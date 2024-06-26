package org.taoding.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.taoding.entity.DishFlavor;

import java.util.List;

/**
 * @Date 6/26/24 11:46
 * @Author Tao Ding
 * @Description: TODO
 */
@Mapper
public interface DishFlavorMapper {
    /**
     * 批量插入口味
     * @param flavors
     */
    void insertBatch(List<DishFlavor> flavors);

    /**
     * 根据dishId删除口味
     * @param dishIds
     */
    void deleteByDishIds(List<Long> dishIds);

    /**
     * 根据菜品id查找口味
     * @param dishId
     * @return
     */
    @Select("select * from dish_flavor where dish_id = #{dishId}")
    List<DishFlavor> getByDishId(Long dishId);
}
