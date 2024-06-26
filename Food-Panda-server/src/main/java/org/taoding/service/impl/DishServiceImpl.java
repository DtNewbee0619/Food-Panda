package org.taoding.service.impl;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.taoding.constant.StatusConstant;
import org.taoding.dto.DishDTO;
import org.taoding.entity.Dish;
import org.taoding.entity.DishFlavor;
import org.taoding.mapper.DishFlavorMapper;
import org.taoding.mapper.DishMapper;
import org.taoding.service.DishService;

import java.util.List;

/**
 * @Date 6/25/24 21:23
 * @Author Tao Ding
 * @Description: TODO
 */
@Slf4j
@Service
@Transactional
public class DishServiceImpl implements DishService {
    @Resource
    private DishMapper dishMapper;

    @Resource
    private DishFlavorMapper dishFlavorMapper;

    @Override
    public void saveWithFlavor(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO,dish);
        dish.setStatus(StatusConstant.DISABLE);
        //插入一条菜品数据
        dishMapper.insert(dish);

        Long dishId = dish.getId();

        //插入口味数据
        List<DishFlavor> flavors = dishDTO.getFlavors();
        if(flavors!=null && !flavors.isEmpty()){
            flavors.forEach(dishFlavor -> dishFlavor.setDishId(dishId));
            //批量插入
            dishFlavorMapper.insertBatch(flavors);
        }


    }
}
