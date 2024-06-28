package org.taoding.controller.user;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.taoding.constant.StatusConstant;
import org.taoding.entity.Dish;
import org.taoding.result.Result;
import org.taoding.service.DishService;
import org.taoding.vo.DishVO;

import java.util.List;

@RestController("userDishController")
@RequestMapping("/user/dish")
@Slf4j
@Tag(name = "C端-菜品浏览接口")
public class DishController {
    @Resource
    private DishService dishService;

    @Resource
    private RedisTemplate redisTemplate;
    /**
     * 根据分类id查询菜品
     *
     * @param categoryId
     * @return
     */
    @GetMapping("/list")
    @Operation(summary = "根据分类id查询菜品")
    public Result<List<DishVO>> list(Long categoryId) {
        //构造redis中你ey,规则:dish分类id
        String key = "dish_"+categoryId;
        //查询redis中是否存在菜品数据
        List<DishVO> list = (List<DishVO>) redisTemplate.opsForValue().get(key);
        if (list != null) {
            //如果存在，直接返回，无须查询数据库
            return Result.success(list);
        }
        Dish dish = new Dish();
        dish.setCategoryId(categoryId);
        //查询起售中的菜品
        dish.setStatus(StatusConstant.ENABLE);

        list = dishService.listWithFlavor(dish);
        //如果不存在，查询数据库，将查询到的数据放入redis中
        redisTemplate.opsForValue().set(key,list);
        return Result.success(list);
    }

}
