package org.taoding.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.taoding.dto.DishDTO;
import org.taoding.dto.DishPageQueryDTO;
import org.taoding.result.PageResult;
import org.taoding.result.Result;
import org.taoding.service.DishService;

import java.util.List;

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
    @Operation(summary = "新增菜品")
    public Result save(@RequestBody DishDTO dishDTO){
        log.info("新增菜品：{}",dishDTO);
        dishService.saveWithFlavor(dishDTO);
        return null;
    }

    @GetMapping("/page")
    @Operation(summary = "分页查询")
    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO){
        log.info("分页查询：{}", dishPageQueryDTO);
        PageResult pageResult = dishService.pageQuery(dishPageQueryDTO);
        return Result.success(pageResult);
    }

    @DeleteMapping
    @Operation(summary = "删除菜品")
    public Result delete(@RequestParam List<Long> ids){
        log.info("删除菜品：{}", ids);
        dishService.deleteById(ids);
        return Result.success();
    }
}
