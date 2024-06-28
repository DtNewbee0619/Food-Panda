package org.taoding.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.taoding.constant.JwtClaimsConstant;
import org.taoding.dto.UserLoginDTO;
import org.taoding.entity.User;
import org.taoding.properties.JwtProperties;
import org.taoding.result.Result;
import org.taoding.service.UserService;
import org.taoding.utils.JwtUtil;
import org.taoding.vo.EmployeeLoginVO;
import org.taoding.vo.UserLoginVO;

import java.util.HashMap;
import java.util.Map;

/**
 * @Date 6/28/24 12:07
 * @Author Tao Ding
 * @Description: TODO
 */
@RestController
@RequestMapping("user/user")
@Slf4j
@Tag(name = "用户接口")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private JwtProperties jwtProperties;
    @PostMapping("login")
    @Operation(summary = "用户登录")
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO){
        log.info("微信用户登录：{}", userLoginDTO.getCode());
        User user = userService.login(userLoginDTO);
        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, user.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getUserSecretKey(),
                jwtProperties.getUserTtl(),
                claims);

        UserLoginVO userLoginVO = UserLoginVO.builder()
                .id(user.getId())
                .openid(user.getOpenid())
                .token(token)
                .build();

        return Result.success(userLoginVO);
    }


}
