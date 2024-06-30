package org.taoding.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.taoding.dto.OrdersSubmitDTO;
import org.taoding.result.Result;
import org.taoding.service.OrderService;
import org.taoding.vo.OrderSubmitVO;

/**
 * @Date 6/29/24 18:15
 * @Author Tao Ding
 * @Description: TODO
 */
@RestController("userOrderController")
@RequestMapping("/user/order")
@Slf4j
@Tag(name = "用户端订单接口")
public class OrderController {
    @Resource
    private OrderService orderService;

    @PostMapping("/submit")
    @Operation(summary = "用户提交订单")
    public Result<OrderSubmitVO> submit(@RequestBody OrdersSubmitDTO ordersSubmitDTO) {
        log.info("用户提交订单:{}", ordersSubmitDTO);
        OrderSubmitVO orderSubmitVO = orderService.submitOrder(ordersSubmitDTO);
        return Result.success(orderSubmitVO);
    }
}
