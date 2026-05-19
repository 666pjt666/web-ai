package com.ppjjtt.pjtcodefather.aop;

import cn.hutool.core.util.ObjUtil;
import com.ppjjtt.pjtcodefather.annotation.AuthCheck;
import com.ppjjtt.pjtcodefather.exception.BusinessException;
import com.ppjjtt.pjtcodefather.exception.ErrorCode;
import com.ppjjtt.pjtcodefather.model.entity.User;
import com.ppjjtt.pjtcodefather.model.enums.UserRoleEnum;
import com.ppjjtt.pjtcodefather.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class AuthInterceptor {
    @Resource
    private UserService userService;

    @Around("@annotation(authCheck)")
    public Object doInterceptor(ProceedingJoinPoint joinPoint, AuthCheck authCheck) throws Throwable {
        String mustRole = authCheck.mustRole();
        //
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        //获取登录用户
        User loginUser = userService.getLoginUser(request);
        UserRoleEnum mustRoleEnum = UserRoleEnum.getEnumByValue(mustRole);
        //判断是否需要权限，若不需要权限，直接放行
        if (mustRoleEnum == null) {
            return joinPoint.proceed();
        }
        //以下的代码，必须要有权限，才能通过：
        UserRoleEnum userRoleEnum = UserRoleEnum.getEnumByValue(loginUser.getUserRole());
        //没有权限，直接拒绝
        if(userRoleEnum == null) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        //要求必须有管理员权限，但当前用户没有管理员权限
        if(userRoleEnum.ADMIN.equals(mustRoleEnum) && !userRoleEnum.ADMIN.equals(userRoleEnum)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        //否则就是已经有管理员权限(只适用于只有两种权限)，放行
        return joinPoint.proceed();
    }
}
