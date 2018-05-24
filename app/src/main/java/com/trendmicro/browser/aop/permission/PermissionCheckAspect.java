package com.trendmicro.browser.aop.permission;

import android.content.Context;

import com.tbruyelle.rxpermissions.RxPermissions;
import com.trend.lazyinject.annotation.Inject;
import com.trendmicro.browser.component.AppComponent;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by swift_gan on 2018/5/24.
 */
@Aspect
public class PermissionCheckAspect {

    @Inject(component = AppComponent.class)
    Context context;

    @Pointcut("execution(@com.trendmicro.common.aop.ospermission.PermissionCheck * *(..)) && @annotation(permissionCheck)")
    public void pointcutOnPermissionCheckMethod(PermissionCheck permissionCheck) {

    }
    @Around("pointcutOnPermissionCheckMethod(permissionCheck)")
    public Object permissionCheckAndExecute(ProceedingJoinPoint joinPoint, PermissionCheck permissionCheck) throws Throwable {
        RxPermissions.getInstance(context)
                .request(permissionCheck.value())
                .subscribe(g -> {
                    if (g) {
                        try {
                            joinPoint.proceed();
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }
                    }
                });
        return null;
    }
}
