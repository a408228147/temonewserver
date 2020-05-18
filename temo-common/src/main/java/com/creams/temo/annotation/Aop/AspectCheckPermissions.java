package com.creams.temo.annotation.Aop;

import com.creams.temo.annotation.CheckPermissions;
import com.google.common.collect.Lists;
import org.aspectj.lang.reflect.MethodSignature;
import com.creams.temo.tools.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.*;
import java.util.List;

@Aspect
@Slf4j
@Component
public class AspectCheckPermissions {

    @Pointcut("@annotation(com.creams.temo.annotation.CheckPermissions)")
    public void check() {

    }


    @Before("check()&&@annotation(checkPermissions)")
    public void checkPermissions(JoinPoint joinPoint, CheckPermissions checkPermissions) {
        try {
            Subject subject = ShiroUtils.getSubject();
            if (checkPermissions.role().equals("") && checkPermissions.route().equals("")) {
                MethodSignature sign = (MethodSignature) joinPoint.getSignature();
                Method method = sign.getMethod();
                //类上面的注解
                String classPath = method.getDeclaringClass().getAnnotation(RequestMapping.class).value()[0];
                String methodPath = "";
                //获取方法上的注解
                if (method.getAnnotation(GetMapping.class) != null) {
                    methodPath = method.getAnnotation(GetMapping.class).value()[0];
                } else if (method.getAnnotation(PostMapping.class) != null) {
                    methodPath = method.getAnnotation(PostMapping.class).value()[0];
                }
                String route = classPath + methodPath;
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
