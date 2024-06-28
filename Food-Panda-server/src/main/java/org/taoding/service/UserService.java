package org.taoding.service;

import org.taoding.dto.UserLoginDTO;
import org.taoding.entity.User;

/**
 * @Date 6/28/24 12:25
 * @Author Tao Ding
 * @Description: TODO
 */
public interface UserService {
    User login(UserLoginDTO userLoginDTO);
}
