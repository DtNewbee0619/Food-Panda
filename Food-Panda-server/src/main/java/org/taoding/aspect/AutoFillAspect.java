package org.taoding.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.taoding.annotation.AutoFill;
import org.taoding.constant.AutoFillConstant;
import org.taoding.context.BaseContext;
import org.taoding.enumeration.OperationType;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * @Date 6/25/24 14:57
 * @Author Tao Ding
 * @Description: 自定义切面，实现公共字段自动填充处理逻辑
 */
@Aspect
@Component
public class AutoFillAspect {
    /**
     * 切入点
     */
    @Pointcut("execution(* org.taoding.mapper.*.*(..)) && @annotation(org.taoding.annotation.AutoFill)")
    public void autoFillPointCut() {}


    /**
     * 前置通知
     */
    @Before("autoFillPointCut()")
    public void autoFillBefore(JoinPoint joinPoint) {

        //获取到当前被拦截的方法上的数据库操作类型
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        AutoFill autoFill = signature.getMethod().getAnnotation(AutoFill.class);
        OperationType operationType = autoFill.value();
        //获取到当前被拦截的方法的参数--实体对象
        Object[] args = joinPoint.getArgs();
        if(args==null||args.length==0){
            return;
        }
        
        Object entity = args[0];
        //推备赋值的数据
        LocalDateTime now = LocalDateTime.now();
        Long currentUserId = BaseContext.getCurrentId();

        //根据当前不同的操作类型，为对应的属性通过反射来赋值
        if(operationType==OperationType.INSERT){
            try {
                Method setCreateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_TIME, LocalDateTime.class);
                Method setCreateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_USER, Long.class);
                Method setUpdateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setUpdateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);
                //通过反射为对象属性赋值
                setCreateTime.invoke(entity,now);
                setCreateUser.invoke(entity,currentUserId);
                setUpdateTime.invoke(entity,now);
                setUpdateUser.invoke(entity,currentUserId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(operationType==OperationType.UPDATE){
            try {
                Method setUpdateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);
                Method setUpdateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                setUpdateTime.invoke(entity,now);
                setUpdateUser.invoke(entity,currentUserId);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
