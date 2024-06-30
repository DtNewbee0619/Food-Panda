package org.taoding.mapper;

import org.apache.ibatis.annotations.*;
import org.taoding.entity.ShoppingCart;

import java.util.List;

/**
 * @Date 6/29/24 14:33
 * @Author Tao Ding
 * @Description: TODO
 */
@Mapper
public interface ShoppingCartMapper {

    /**
     * 查询购物车数据
     * @param shoppingCart
     * @return
     */
    List<ShoppingCart> list(ShoppingCart shoppingCart);

    /**
     * 根据用户id查询购物车数据
     * @param userId
     * @return
     */
    @Select("select * from shopping_cart where user_id=#{userId}")
    List<ShoppingCart> listByUserId(Long userId);

    /**
     * 更新数量
     * @param shoppingCart
     */
    @Update("update shopping_cart set number = #{number} where id=#{id}")
    void updateNumberById(ShoppingCart shoppingCart);

    /**
     * 购物车添加数据
     * @param shoppingCart
     */
    @Insert("insert into shopping_cart (name, user_id, dish_id, setmeal_id, dish_flavor, number, amount, image, create_time) " +
            "values (#{name},#{userId},#{dishId},#{setmealId},#{dishFlavor},#{number},#{amount},#{image},#{createTime})")
    void insert(ShoppingCart shoppingCart);

    /**
     * 清空购物车
     * @param userId
     */
    @Delete("delete from shopping_cart where user_id=#{userId}")
    void deleteAll(Long userId);
}
