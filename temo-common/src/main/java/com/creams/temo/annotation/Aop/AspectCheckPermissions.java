package com.creams.temo.annotation.Aop;

import com.creams.temo.annotation.CheckPermissions;
import com.creams.temo.tools.RedisUtil;
import org.aspectj.lang.reflect.MethodSignature;
import com.creams.temo.tools.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.*;

@Aspect
@Slf4j
@Component
public class AspectCheckPermissions {

    @Autowired
    RedisUtil redisUtil;

    @Pointcut("@annotation(com.creams.temo.annotation.CheckPermissions)")
    public void check() {

    }


    @Before("check()&&@annotation(checkPermissions)")
    public void checkPermissions(JoinPoint joinPoint, CheckPermissions checkPermissions) {
        try {
            if (!redisUtil.hasKey("permissions_button")||!(boolean) redisUtil.get("permissions_button")) {
                return;
            }
            Subject subject = ShiroUtils.getSubject();
            if (checkPermissions.role().equals("") && checkPermissions.route().equals("")) {
                MethodSignature sign = (MethodSignature) joinPoint.getSignature();
                Method method = sign.getMethod();
                //类上面的注解
                String requestType = "";
                String classPath = method.getDeclaringClass().getAnnotation(RequestMapping.class).value()[0];
                String methodPath = "";
                //获取方法上的注解
                if (method.getAnnotation(GetMapping.class) != null) {
                    methodPath = method.getAnnotation(GetMapping.class).value()[0];
                    requestType = "Get";
                } else if (method.getAnnotation(PostMapping.class) != null) {
                    methodPath = method.getAnnotation(PostMapping.class).value()[0];
                    requestType = "Post";
                } else if (method.getAnnotation(DeleteMapping.class) != null) {
                    methodPath = method.getAnnotation(DeleteMapping.class).value()[0];
                    requestType = "Delete";
                } else if (method.getAnnotation(PutMapping.class) != null) {
                    methodPath = method.getAnnotation(PutMapping.class).value()[0];
                    requestType = "Put";
                }
                String route = requestType + classPath + methodPath;
                subject.checkPermission(route);
            }
            if (!checkPermissions.role().equals("")) {
                subject.checkRole(checkPermissions.role());
            }
            if (!checkPermissions.route().equals("")) {
                subject.checkPermission(checkPermissions.route());
            }
        } catch (Exception e) {
            throw new RuntimeException("暂无此权限");
        }
    }
}
