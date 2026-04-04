package com.keshavbansal.ECommerceStore.controller;

import com.keshavbansal.ECommerceStore.globalException.ResourceNotFoundException;
import com.keshavbansal.ECommerceStore.model.Cart;
import com.keshavbansal.ECommerceStore.response.ApiResponse;
import com.keshavbansal.ECommerceStore.service.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/get-cart")
    public ResponseEntity<ApiResponse> getCart(@RequestParam Long id){
        try{
            Cart cart =  cartService.getCart(id);
            return ResponseEntity.ok(new ApiResponse("Cart retrieved successfully", cart));
        }catch(ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/clear-cart")
    public ResponseEntity<ApiResponse> clearCart(@RequestParam Long id){
        try{
            cartService.clearCart(id);
            return ResponseEntity.ok(new ApiResponse("Cart Clear successfully", null));
        }catch(ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/total-amount")
    public ResponseEntity<ApiResponse>  getTotalAmount(@RequestParam Long id){
        try{
            BigDecimal amount = cartService.getTotalAmount(id);
            return ResponseEntity.ok(new ApiResponse("Total amount retrieved successfully", amount));
        }catch(ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
