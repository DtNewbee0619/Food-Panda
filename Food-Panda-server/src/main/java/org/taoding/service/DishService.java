package org.taoding.service;

import org.taoding.dto.DishDTO;
import org.taoding.dto.DishPageQueryDTO;
import org.taoding.result.PageResult;

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
}
