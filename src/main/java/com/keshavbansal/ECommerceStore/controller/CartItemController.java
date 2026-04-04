package com.keshavbansal.ECommerceStore.controller;

import com.keshavbansal.ECommerceStore.globalException.ResourceNotFoundException;
import com.keshavbansal.ECommerceStore.model.CartItem;
import com.keshavbansal.ECommerceStore.response.ApiResponse;
import com.keshavbansal.ECommerceStore.service.cart.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/cartItem")
@RequiredArgsConstructor
public class CartItemController {

    private final CartItemService cartItemService;


    @GetMapping("/get-cartItem")
    public ResponseEntity<ApiResponse> getCartItem(@RequestParam Long cartId,
                                                   @RequestParam Long productId){
        try {
            CartItem cartItem = cartItemService.getCartItem(cartId, productId);
            return ResponseEntity.ok(new ApiResponse("Item retrieved From cart successfully", cartItem));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PostMapping("/add-item")
    public ResponseEntity<ApiResponse> addItemToCart(@RequestParam Long cartId,
                                                     @RequestParam Long productId,
                                                     @RequestParam Integer quantity){

        try {
            cartItemService.addItemtoCart(cartId, productId, quantity);
            return ResponseEntity.ok(new ApiResponse("Item added to cart successfully", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }

    }

    @PutMapping("/update-cart-item")
    public ResponseEntity<ApiResponse> updateItemQuantity(@RequestParam Long cartId,
                                                        @RequestParam Long productId,
                                                        @RequestParam Integer quantity){
        try {
            cartItemService.updateItemQuantity(cartId, productId,quantity);
            return ResponseEntity.ok(new ApiResponse("Item updated in cart successfully", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/remove-cart-item")
    public ResponseEntity<ApiResponse> removeItemFromCart(@RequestParam Long cartId,
                                                          @RequestParam Long productId){

        try {
            cartItemService.removeItemfromCart(cartId, productId);
            return ResponseEntity.ok(new ApiResponse("Item removed From cart successfully", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

}
