package org.taoding.service;

import org.taoding.dto.DishDTO;
import org.taoding.dto.DishPageQueryDTO;
import org.taoding.entity.Dish;
import org.taoding.result.PageResult;
import org.taoding.vo.DishVO;

import java.util.List;

/**
 * @Date 6/25/24 21:22
 * @Author Tao Ding
 * @Description: TODO
 */
public interface DishService {
    /**
     * 保存菜品
     * @param dishDTO
     */
    void saveWithFlavor(DishDTO dishDTO);

    /**
     * 分页查询
     * @param dishPageQueryDTO
     * @return
     */
    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 根据id删除分类
     * @param ids
     */
    void deleteById(List<Long> ids);

    /**
     * 根据id查找菜品
     * @param id
     * @return
     */
    DishVO getById(Long id);

    /**
     * 修改菜品
     * @param dishDTO
     */
    void update(DishDTO dishDTO);
}
