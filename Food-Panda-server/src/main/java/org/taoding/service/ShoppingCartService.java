package org.taoding.service;

import org.taoding.dto.ShoppingCartDTO;
import org.taoding.entity.ShoppingCart;

import java.util.List;

/**
 * @Date 6/29/24 14:32
 * @Author Tao Ding
 * @Description: TODO
 */
public interface ShoppingCartService {
    void addShoppingCart(ShoppingCartDTO shoppingCartDTO);

    List<ShoppingCart> getShoppingCartList();
}
