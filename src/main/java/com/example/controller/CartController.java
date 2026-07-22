package com.example.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.dto.cart.CartRequest;
import com.example.dto.cart.CartResponse;
import com.example.dto.cart.CartUpdateRequest;
import com.example.service.CartService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/items")
    public CartResponse addItemToCart(@Valid @RequestBody CartRequest request) {
        System.out.println("CartController.addItemToCart()");
    	return cartService.addItemToCart(request);
    }

    @GetMapping
    public CartResponse getCart(@RequestParam Long CartId) {
    	System.out.println("CartController.getCart()");
        return cartService.getCart(CartId);
    }

    @PutMapping("/items/{id}")
    public CartResponse updateCartItem(@PathVariable Long id, @Valid @RequestBody CartUpdateRequest request) {
    	System.out.println("CartController.updateCartItem()");
        return cartService.updateCartItem(id, request);
    }

    @DeleteMapping("/items/{id}")
    public String removeCartItem(@PathVariable Long id) {
    	System.out.println("CartController.removeCartItem()");
        cartService.removeCartItem(id);
        return "Item Removed Successfully";
    }

    @DeleteMapping("/clear")
    public String clearCart(@RequestParam Long userId) {
    	System.out.println("CartController.clearCart()");
        cartService.clearCart(userId);
        return "Cart Cleared Successfully";
    }
}
