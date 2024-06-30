package org.taoding.task;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.taoding.constant.MessageConstant;
import org.taoding.entity.Orders;
import org.taoding.mapper.OrderMapper;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Date 6/30/24 13:27
 * @Author Tao Ding
 * @Description: 定时处理订单状态
 */
@Component
@Slf4j
@Transactional
public class OrderTask {
    @Resource
    private OrderMapper orderMapper;

    @Scheduled(cron = "0 * * * * ?")
    public void processTimeOutOrder() {
        log.info("处理超时订单：{}", LocalDateTime.now());
        List<Orders> orders = orderMapper.selectByStatusAndOrderTime(Orders.PENDING_PAYMENT, LocalDateTime.now().minusMinutes(15));
        if(orders != null && !orders.isEmpty()) {
            orders.forEach(order -> {
                order.setStatus(Orders.CANCELLED);
                order.setCancelReason(MessageConstant.ORDER_TIME_OUT);
                order.setCancelTime(LocalDateTime.now());
                orderMapper.update(order);
            });
        }
    }
    @Scheduled(cron = "0 0 1 * * ?")
    public void processCompleteOrder() {
        log.info("处理已经完成的订单{}", LocalDateTime.now());
        List<Orders> orders = orderMapper.selectByStatusAndOrderTime(Orders.DELIVERY_IN_PROGRESS, LocalDateTime.now().minusHours(1));
        if(orders != null && !orders.isEmpty()) {
            orders.forEach(order -> {
                order.setStatus(Orders.COMPLETED);
                orderMapper.update(order);
            });
        }
    }
}
