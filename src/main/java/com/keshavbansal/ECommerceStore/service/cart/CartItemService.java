package com.keshavbansal.ECommerceStore.service.cart;

import com.keshavbansal.ECommerceStore.globalException.ResourceNotFoundException;
import com.keshavbansal.ECommerceStore.model.Cart;
import com.keshavbansal.ECommerceStore.model.CartItem;
import com.keshavbansal.ECommerceStore.model.Product;
import com.keshavbansal.ECommerceStore.repository.CartItemRepository;
import com.keshavbansal.ECommerceStore.repository.CartRepository;
import com.keshavbansal.ECommerceStore.service.product.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CartItemService implements ICartItemService {

    private final CartItemRepository cartItemRepository;

    private final CartService cartService;

    private final IProductService productService;

    private final CartRepository cartRepository;

    @Override
    public void addItemtoCart(Long cartId, Long productId, int quantity) {

        // 1. Get Cart
        // 2. Get Product
        // 3. Check if the product already Exist in cart
        // 4. If exist then update the quantity and total price
        // 5. If not exist then create a new CartItem and add to cart

        Cart cart = cartService.getCart(cartId);
        Product product = productService.getProductById(productId);

        CartItem cartItem = cart.getCartItems()
                .stream().filter(items-> items.getProduct().getId().equals(productId))
                .findFirst().orElse(new CartItem());

        if (cartItem.getId() == null) {
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setUnitPrice(product.getPrice());
        }
        else{
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        }

        cartItem.setTotalPrice();
        cart.addItem(cartItem);
        cartItemRepository.save(cartItem);
        cartRepository.save(cart);

    }

    @Override
    public void removeItemfromCart(Long cartId, Long productId) {

        Cart cart = cartService.getCart(cartId);

        CartItem itemToRemove = getCartItem(cartId, productId);

        cart.removeItem(itemToRemove);
        cartRepository.save(cart);

    }

    @Override
    public void updateItemQuantity(Long cartId, Long productId, int quantity) {

        Cart cart = cartService.getCart(cartId);

        cart.getCartItems()
                .stream().filter(items-> items.getProduct().getId().equals(productId)).findFirst()
                .ifPresent(items -> {
                    items.setQuantity(quantity);
                    items.setUnitPrice(items.getProduct().getPrice());
                    items.setTotalPrice();
                });

        BigDecimal totalPrice = cart.getTotalAmount();
        cart.setTotalAmount(totalPrice);
        cartRepository.save(cart);
    }

    @Override
    public CartItem getCartItem(Long cartId, Long productId){

        Cart cart = cartService.getCart(cartId);

        return cart.getCartItems()
                .stream().filter(items-> items.getProduct().getId().equals(productId)).findFirst()
                .orElseThrow(()->new ResourceNotFoundException("Product with id " + productId + " not found in cart with id " + cartId));
    }
}
