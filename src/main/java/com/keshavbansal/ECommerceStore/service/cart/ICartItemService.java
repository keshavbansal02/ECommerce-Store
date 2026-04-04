package com.keshavbansal.ECommerceStore.service.cart;

import com.keshavbansal.ECommerceStore.model.CartItem;

public interface ICartItemService {

    void addItemtoCart(Long cartId, Long productId, int quantity);

    void removeItemfromCart(Long cartId, Long productId);

    void updateItemQuantity(Long cartId, Long productId, int quantity);

    CartItem getCartItem(Long cartId, Long productId);
}
