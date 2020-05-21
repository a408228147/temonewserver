package com.creams.temo.util;

import com.creams.temo.biz.UserRoleService;
import com.creams.temo.biz.UserService;
import com.creams.temo.entity.UserInfo;
import com.creams.temo.entity.UserRoleEntity;
import com.creams.temo.model.PermissionsBo;
import com.creams.temo.model.RoleBo;
import com.creams.temo.model.UserBo;
import com.creams.temo.tools.RedisUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.catalina.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class CustomRealm extends AuthorizingRealm {

    @Autowired
    UserService loginService;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    RedisUtil redisUtil;

    /**
     * 授权(验证权限时调用)
     * 获取用户权限集合
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录用户名
        UserInfo userInfo = (UserInfo) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        if (redisUtil.hasKey(userInfo.getUserId())) {
            Map map =(Map) redisUtil.get(userInfo.getUserId());
            simpleAuthorizationInfo.addRoles((List<String>)map.get("role"));
            simpleAuthorizationInfo.addStringPermissions((List<String>)map.get("permissions"));
            return  simpleAuthorizationInfo;
        } else {
            Map<String, List<String>> map = Maps.newHashMap();
            //添加角色和权限
            List<UserRoleEntity> userRoleEntitys = userRoleService.queryRoleByUserId(userInfo.getUserId());
            List<String> roles = userRoleEntitys.stream().map(i -> i.getRoleName()).collect(Collectors.toList());
            simpleAuthorizationInfo.addRoles(roles);
            //添加该用户拥有user角色
            List<String> permissions = Lists.newArrayList();
            for (UserRoleEntity role : userRoleEntitys) {
                //添加该用户拥有query权限
                List<PermissionsBo> listPer = loginService.queryPermissionsByRoleId(role.getRoleId());
                permissions = listPer.stream().map(i -> i.getPermissionsRoute()).collect(Collectors.toList());
            }
            simpleAuthorizationInfo.addStringPermissions(permissions);
            map.put("role", roles);
            map.put("permissions", permissions);
            redisUtil.set(userInfo.getUserId(), map, 3600);
        }
        return simpleAuthorizationInfo;
    }


    /**
     * 认证(登录时调用)
     * 验证用户登录
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {


        //加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        if (authenticationToken.getPrincipal() == null) {
            return null;
        }
        //获取用户信息
        //UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String userName = authenticationToken.getPrincipal().toString();
        UserBo user = loginService.queryUserByName(userName);
        UserInfo userInfo = UserInfo.builder()
                .email(user.getEmail())
                .id(user.getId())
                .password(user.getPassword())
                .status(user.getStatus())
                .userId(user.getUserId())
                .userName(user.getUserName()).build();
        if (user == null) {
            //这里返回后会报出对应异常
            throw new LockedAccountException("账户不存在,请联系管理员");
        } else {
            //这里验证authenticationToken和simpleAuthenticationInfo的信息
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo
                    (userInfo,
                            userInfo.getPassword().toString(),
                            ByteSource.Util.bytes(userInfo.getUserId()), getName());
            return simpleAuthenticationInfo;
        }

    }
}
