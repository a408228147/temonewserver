package com.creams.temo.controller;

import com.creams.temo.biz.UserServer;
import com.creams.temo.convert.UserBo2UserAo;
import com.creams.temo.model.LoginAo;
import com.creams.temo.model.UserAo;
import com.creams.temo.model.UserBo;
import com.creams.temo.model.result.JsonResult;
import com.creams.temo.util.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class LoginController {

    final UserBo2UserAo userBo2UserAo = UserBo2UserAo.getInstance();

    @Autowired
    UserServer userBiz;


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
            return new JsonResult("登入成功", 200, userInfo, true);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return new JsonResult("账号密码错误", 500, null, false);
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
