package org.taoding.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Date 6/26/24 15:47
 * @Author Tao Ding
 * @Description: TODO
 */
@Mapper
public interface SetmealDishMapper {
    /**
     * 根据菜品id查询套餐id
     * @param dishIds
     * @return
     */
    List<Long> selectSetmealIdsByDishIds(List<Long> dishIds);
}
