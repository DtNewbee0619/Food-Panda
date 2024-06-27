package org.taoding.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.taoding.constant.StatusConstant;
import org.taoding.result.Result;

/**
 * @Date 6/27/24 16:30
 * @Author Tao Ding
 * @Description: TODO
 */
@RestController("userShopController")
@RequestMapping("user/shop")
@Slf4j
@Tag(name = "店铺接口")
public class ShopController {
    @Resource
    private RedisTemplate redisTemplate;


    @GetMapping("/status")
    @Operation(summary = "获取店铺状态")
    public Result<Integer> getStatus(){
        Integer shopStatus = (Integer) redisTemplate.opsForValue().get(StatusConstant.SHOP);
        log.info("获取到店铺的营业状态为：{}", shopStatus);
        return Result.success(shopStatus);
    }
}
