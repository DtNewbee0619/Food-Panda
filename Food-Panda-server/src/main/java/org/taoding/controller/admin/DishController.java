package org.taoding.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.taoding.dto.DishDTO;
import org.taoding.result.Result;
import org.taoding.service.DishService;

/**
 * @Date 6/25/24 21:13
 * @Author Tao Ding
 * @Description: 菜品管理
 */
@RestController
@RequestMapping("admin/dish")
@Slf4j
@Tag(name = "菜品接口")
public class DishController {
    @Resource
    private DishService dishService;

    @PostMapping
    @Operation(description = "新增菜品")
    public Result save(@RequestBody DishDTO dishDTO){
        log.info("新增菜品：{}",dishDTO);
        dishService.saveWithFlavor(dishDTO);
        return null;
    }

}
