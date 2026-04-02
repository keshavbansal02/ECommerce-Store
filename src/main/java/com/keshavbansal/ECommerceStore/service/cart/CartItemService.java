package com.keshavbansal.ECommerceStore.service.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemService implements ICartItemService {

    private final ICartService cartService;

    @Override
    public void addItemtoCart(Long cartId, Long productId, int quantity) {

    }

    @Override
    public void removeItemfromCart(Long cartId, Long productId) {

    }

    @Override
    public void updateItemInCart(Long cartId, Long productId, int quantity) {

    }
}
