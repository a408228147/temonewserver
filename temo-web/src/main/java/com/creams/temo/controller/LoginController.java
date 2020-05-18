package com.creams.temo.controller;

import com.creams.temo.annotation.CheckPermissions;
import com.creams.temo.biz.UserRoleService;
import com.creams.temo.biz.UserService;
import com.creams.temo.convert.UserAo2UserBo;
import com.creams.temo.entity.UserInfo;
import com.creams.temo.entity.UserRoleEntity;
import com.creams.temo.entity.result.JsonResult;
import com.creams.temo.model.LoginAo;
import com.creams.temo.model.PermissionsBo;
import com.creams.temo.model.UserAo;
import com.creams.temo.model.UserBo;
import com.creams.temo.tools.RedisUtil;
import com.creams.temo.tools.ShiroUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class LoginController {

    final UserAo2UserBo userBo2UserAo = UserAo2UserBo.getInstance();

    @Autowired
    UserService userBiz;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    UserRoleService userRoleService;
    @Autowired
    UserService loginService;

    @PostMapping("/login")
    public JsonResult login(@RequestBody LoginAo user) {
        //添加用户认证信息
        Subject subject = ShiroUtils.getSubject();
        UserBo userBo = userBiz.queryUserByName(user.getUserName());
        UserAo userAo = userBo2UserAo.convert(userBo);
        if (userAo == null) {
            return new JsonResult("该用户不存在！", 500, null, false);
        }
        if (userAo.getStatus() == 0) {
            return new JsonResult("该用户已被禁用！请联系后台人员开启！", 500, null, false);
        }
        String shairPwd = ShiroUtils.sha256(user.getPassword(), userAo.getUserId());
        userAo.setPassword(shairPwd);
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                userAo.getUserName(),
                userAo.getPassword()
        );
        Map<Object, Object> userInfo = new HashMap<>();
        userInfo.put("userId", userAo.getUserId());
        userInfo.put("userName", userAo.getUserName());
        try {
            //进行验证，这里可以捕获异常，然后返回对应信息
            subject.login(usernamePasswordToken);
            //更新用户权限
            flushPermissions();
            return new JsonResult("登入成功", 200, userInfo, true);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return new JsonResult("账号密码错误", 500, null, false);
        }
    }

    @PostMapping("/flushPermissions")
    public JsonResult flushPermissions() {
        try {
            if (ShiroUtils.getUserEntity() == null) {
                return new JsonResult("未登陆", 500, null, true);
            }
            String userId = ShiroUtils.getUserId();
            Map<String, List<String>> map = Maps.newHashMap();
            //添加角色和权限
            List<UserRoleEntity> userRoleEntitys = userRoleService.queryRoleByUserId(userId);
            List<String> roles = userRoleEntitys.stream().map(i -> i.getRoleName()).collect(Collectors.toList());
            //添加该用户拥有user角色
            List<String> permissions = Lists.newArrayList();
            for (UserRoleEntity role : userRoleEntitys) {
                //添加该用户拥有query权限
                List<PermissionsBo> listPer = loginService.queryPermissionsByRoleId(role.getRoleId());
                permissions = listPer.stream().map(i -> i.getPermissionsRoute()).collect(Collectors.toList());
            }
            map.put("role", roles);
            map.put("permissions", permissions);
            redisUtil.set(userId, map, 3600);
            return new JsonResult("刷新成功", 200, null, true);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return new JsonResult("刷新失败", 500, null, false);
        }
    }

    @PostMapping("/logout")
    public JsonResult logout() {
        //添加用户认证信息
        Subject subject = ShiroUtils.getSubject();
        try {
            subject.logout();
            return new JsonResult("登出成功", 200, null, true);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return new JsonResult("登出失败", 500, null, false);
        }
    }
}
