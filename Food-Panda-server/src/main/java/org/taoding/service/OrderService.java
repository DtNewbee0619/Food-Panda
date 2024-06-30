package org.taoding.service;

import org.taoding.dto.OrdersSubmitDTO;
import org.taoding.vo.OrderSubmitVO;

/**
 * @Date 6/29/24 18:19
 * @Author Tao Ding
 * @Description: TODO
 */
public interface OrderService {
    OrderSubmitVO submitOrder(OrdersSubmitDTO ordersSubmitDTO);
}
