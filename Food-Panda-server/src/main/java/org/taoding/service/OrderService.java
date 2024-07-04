package org.taoding.service;

import org.taoding.dto.OrdersPaymentDTO;
import org.taoding.dto.OrdersSubmitDTO;
import org.taoding.vo.OrderPaymentVO;
import org.taoding.vo.OrderSubmitVO;

/**
 * @Date 6/29/24 18:19
 * @Author Tao Ding
 * @Description: TODO
 */
public interface OrderService {
    /**
     * 提交订单
     * @param ordersSubmitDTO
     * @return
     */
    OrderSubmitVO submitOrder(OrdersSubmitDTO ordersSubmitDTO);

    /**
     * 订单支付
     * @param ordersPaymentDTO
     * @return
     */
    OrderPaymentVO payment(OrdersPaymentDTO ordersPaymentDTO) throws Exception;

    /**
     * 支付成功，修改订单状态
     * @param outTradeNo
     */
    void paySuccess(String outTradeNo);

    /**
     * 催单
     * @param id
     */
    void reminder(Long id);
}
