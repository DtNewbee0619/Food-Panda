package org.taoding.aspect;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.taoding.constant.ClearCacheConstant;

import java.lang.reflect.Method;
import java.util.Set;

/**
 * @Date 6/28/24 21:09
 * @Author Tao Ding
 * @Description: TODO
 */
@Aspect
@Component
@Slf4j
public class ClearCacheAspect {
    @Resource
    private RedisTemplate redisTemplate;

    @Pointcut("execution(* org.taoding.controller.admin.DishController.startOrStop(..)) || " +
            "execution(* org.taoding.controller.admin.DishController.update(..))")
    public void clearAllCachePointCut() {}

    @Pointcut("execution(* org.taoding.controller.admin.DishController.save(..))")
    public void clearSingleCachePointCut() {}


    /**
     * 清理全部redis缓存
     */
    @AfterReturning("clearAllCachePointCut()")
    public void clearAllCache(){
        Set keys = redisTemplate.keys("dish_*");
        redisTemplate.delete(keys);

    }

    /**
     * 清理单个分类缓存
     * @param joinPoint
     */
    @AfterReturning("clearSingleCachePointCut()")
    public void clearSingleCache(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        if(args==null||args.length==0){
            return;
        }
        try {
            Object entity = args[0];
            //清理缓存数据
            Method getCategoryId = entity.getClass().getDeclaredMethod(ClearCacheConstant.GET_CATEGORY_ID);
            Long id = (Long) getCategoryId.invoke(entity);
            String key = "dish_"+ id;
            redisTemplate.delete(key);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
