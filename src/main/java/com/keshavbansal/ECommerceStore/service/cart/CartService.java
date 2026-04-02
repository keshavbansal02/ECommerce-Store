package com.keshavbansal.ECommerceStore.service.cart;

import com.keshavbansal.ECommerceStore.globalException.ResourceNotFoundException;
import com.keshavbansal.ECommerceStore.model.Cart;
import com.keshavbansal.ECommerceStore.model.CartItem;
import com.keshavbansal.ECommerceStore.repository.CartItemRepository;
import com.keshavbansal.ECommerceStore.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CartService implements ICartService {

    private final CartRepository cartRepository;

    private final CartItemRepository cartItemRepository;

    @Override
    public Cart getCart(Long id) {
        Cart cart  = cartRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Cart with id " + id + " not found"));
        BigDecimal totalAmount = cart.getTotalAmount();
        cart.setTotalAmount(totalAmount);
        return cartRepository.save(cart);
    }

    @Override
    public void clearCart(Long id) {

        Cart cart = getCart(id);

        cartItemRepository.deleteAllByCartId(id);

        cart.getCartItems().clear();

        cartRepository.deleteById(id);

    }

    @Override
    public BigDecimal getTotalAmount(Long id) {
        Cart cart = getCart(id);
        return cart.getTotalAmount();
    }
}
