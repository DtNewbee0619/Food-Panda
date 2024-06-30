package org.taoding.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.taoding.entity.Orders;

/**
 * @Date 6/29/24 21:19
 * @Author Tao Ding
 * @Description: TODO
 */
@Mapper
public interface OrderMapper {
    /**
     * 插入订单数据
     * @param order
     */
    void insert(Orders order);
}
