package org.taoding.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import jakarta.annotation.Resource;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.taoding.constant.MessageConstant;
import org.taoding.dto.UserLoginDTO;
import org.taoding.entity.User;
import org.taoding.exception.LoginFailedException;
import org.taoding.mapper.UserMapper;
import org.taoding.properties.WeChatProperties;
import org.taoding.service.UserService;
import org.taoding.utils.HttpClientUtil;

import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * @Date 6/28/24 12:25
 * @Author Tao Ding
 * @Description: TODO
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Resource
    private WeChatProperties weChatProperties;
    @Resource
    private UserMapper userMapper;
    public static final String WX_LOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session";

    /**
     * 微信用户登录
     * @param userLoginDTO
     * @return
     */
    @Override
    public User login(UserLoginDTO userLoginDTO) {
        //获取code
        String code = userLoginDTO.getCode();
        String openid = getOpenId(code);

        if (openid == null || openid.isEmpty()) {
            throw new LoginFailedException(MessageConstant.LOGIN_FAILED);
        }
        User user = userMapper.selectByOpenId(openid);
        if(user == null){
            user = User.builder()
                    .openid(openid)
                    .createTime(LocalDateTime.now())
                    .build();
            userMapper.insert(user);
        }
        return user;

    }

    /**
     * 调用微信接口服务，获取微信用户openid
     * @param code
     * @return
     */
    private String getOpenId(String code) {
        //发送验证请求
        HashMap<String,String> parameters = new HashMap<>();
        parameters.put("appid", weChatProperties.getAppid());
        parameters.put("secret", weChatProperties.getSecret());
        parameters.put("grant_type", "authorization_code");
        parameters.put("js_code", code);
        String response = HttpClientUtil.doGet(WX_LOGIN_URL, parameters);

        JSONObject jsonObject = JSON.parseObject(response);
        return jsonObject.getString("openid");
    }
}
