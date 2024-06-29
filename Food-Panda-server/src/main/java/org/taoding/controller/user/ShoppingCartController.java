package org.taoding.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.taoding.dto.ShoppingCartDTO;
import org.taoding.entity.ShoppingCart;
import org.taoding.result.Result;
import org.taoding.service.ShoppingCartService;

import java.util.List;

/**
 * @Date 6/29/24 14:07
 * @Author Tao Ding
 * @Description: TODO
 */
@RestController
@RequestMapping("user/shoppingCart")
@Slf4j
@Tag(name = "购物车接口")
public class ShoppingCartController  {
    @Resource
    private ShoppingCartService shoppingCartService;

    @PostMapping("/add")
    @Operation(summary = "购物车添加商品")
    public Result add(@RequestBody ShoppingCartDTO shoppingCartDTO){
        log.info("购物车添加商品：{}",shoppingCartDTO);
        shoppingCartService.addShoppingCart(shoppingCartDTO);
        return Result.success();
    }

    @GetMapping("/list")
    @Operation(summary = "购物车查询")
    public Result<List<ShoppingCart>> getShoppingCartList(){
        List<ShoppingCart> cart = shoppingCartService.getShoppingCartList();
        log.info("获取购物车商品：{}", cart);
        return Result.success(cart);
    }
}
