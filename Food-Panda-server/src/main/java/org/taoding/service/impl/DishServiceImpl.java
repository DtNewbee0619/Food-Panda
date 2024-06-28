package org.taoding.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.taoding.constant.MessageConstant;
import org.taoding.constant.StatusConstant;
import org.taoding.dto.DishDTO;
import org.taoding.dto.DishPageQueryDTO;
import org.taoding.entity.Dish;
import org.taoding.entity.DishFlavor;
import org.taoding.exception.DeletionNotAllowedException;
import org.taoding.mapper.DishFlavorMapper;
import org.taoding.mapper.DishMapper;
import org.taoding.mapper.SetmealDishMapper;
import org.taoding.result.PageResult;
import org.taoding.service.DishService;
import org.taoding.vo.DishVO;

import java.util.ArrayList;
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

    @Resource
    private SetmealDishMapper setmealDishMapper;

    @Override
    public void saveWithFlavor(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO,dish);
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

    @Override
    @Transactional(readOnly = true)
    public PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO) {
        PageHelper.startPage(dishPageQueryDTO.getPage(),dishPageQueryDTO.getPageSize());
        Page<DishVO> page = dishMapper.selectByPage(dishPageQueryDTO);
        long total = page.getTotal();
        List<DishVO> result = page.getResult();
        return new PageResult(total,result);
    }

    @Override
    public void deleteById(List<Long> ids) {
        List<Dish> onSaledishes = dishMapper.selectOnSale(ids);
        if(onSaledishes!=null && !onSaledishes.isEmpty()){
            throw new DeletionNotAllowedException(MessageConstant.DISH_ON_SALE);
        }
        List<Long> setmealIds = setmealDishMapper.selectSetmealIdsByDishIds(ids);
        if(setmealIds!=null && !setmealIds.isEmpty()){
            throw new DeletionNotAllowedException(MessageConstant.SETMEAL_ON_SALE);
        }
        dishMapper.deleteBatch(ids);
        dishFlavorMapper.deleteByDishIds(ids);
    }

    @Override
    public DishVO getById(Long id) {
        Dish dish = dishMapper.selectById(id);
        //根据菜品id查询口味数据
        List<DishFlavor> dishFlavors = dishFlavorMapper.getByDishId(id);
        DishVO dishVO = new DishVO();
        BeanUtils.copyProperties(dish, dishVO);
        dishVO.setFlavors(dishFlavors);
        return dishVO;
    }

    /**
     * 修改菜品
     * @param dishDTO
     */
    @Override
    public void update(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO,dish);
        //修改菜品表基本信息
        dishMapper.update(dish);
        //删除原有的口味数据
        dishFlavorMapper.deleteByDishIds(List.of(dishDTO.getId()));
        //重新插入口味数据
        List<DishFlavor> flavors = dishDTO.getFlavors();
        if(flavors!=null && !flavors.isEmpty()){
            flavors.forEach(dishFlavor -> dishFlavor.setDishId(dishDTO.getId()));
            //批量插入
            dishFlavorMapper.insertBatch(flavors);
        }
    }

    /**
     * 条件查询菜品和口味
     * @param dish
     * @return
     */
    @Override
    public List<DishVO> listWithFlavor(Dish dish) {
        List<Dish> dishList = dishMapper.list(dish);

        List<DishVO> dishVOList = new ArrayList<>();

        for (Dish d : dishList) {
            DishVO dishVO = new DishVO();
            BeanUtils.copyProperties(d,dishVO);

            //根据菜品id查询对应的口味
            List<DishFlavor> flavors = dishFlavorMapper.getByDishId(d.getId());

            dishVO.setFlavors(flavors);
            dishVOList.add(dishVO);
        }

        return dishVOList;
    }

    /**
     * 根据分类id查询菜品
     * @param categoryId
     * @return
     */
    public List<Dish> list(Long categoryId) {
        Dish dish = Dish.builder()
                .categoryId(categoryId)
                .status(StatusConstant.ENABLE)
                .build();
        return dishMapper.list(dish);
    }

    /**
     * 启用禁用菜品
     * @param status
     * @param id
     */
    @Override
    public void startOrStop(Integer status, Long id) {
        Dish dish = Dish.builder()
                .id(id)
                .status(status)
                .build();
        dishMapper.update(dish);
    }
}
