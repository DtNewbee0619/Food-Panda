package org.taoding.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.taoding.entity.User;

/**
 * @Date 6/28/24 13:07
 * @Author Tao Ding
 * @Description: TODO
 */
@Mapper
public interface UserMapper {

    /**
     * 根据openid查询用户
     * @param openid
     * @return
     */
    @Select("select * from user where openid=#{openid}")
    User selectByOpenId(String openid);

    void insert(User user);
}
