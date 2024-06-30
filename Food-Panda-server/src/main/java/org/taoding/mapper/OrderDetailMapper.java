package org.taoding.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.taoding.entity.OrderDetail;

import java.util.List;

/**
 * @Date 6/29/24 21:19
 * @Author Tao Ding
 * @Description: TODO
 */
@Mapper
public interface OrderDetailMapper {
    /**
     * 添加订单菜品明细
     * @param orderDetailList
     */
    void insertBatch(List<OrderDetail> orderDetailList);
}
