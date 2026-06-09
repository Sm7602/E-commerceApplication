package com.example.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.entity.Cart;
import com.example.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/items")
    public Cart addItemToCart( @RequestParam Long userId, @RequestParam Long productId, @RequestParam Integer quantity) {
        System.out.println("CartController.addItemToCart()");
    	return cartService.addItemToCart(userId,productId,quantity);
    }

    @GetMapping
    public Cart getCart(@RequestParam Long userId) {
    	System.out.println("CartController.getCart()");
        return cartService.getCart(userId);
    }

    @PutMapping("/items/{id}")
    public Cart updateCartItem(@PathVariable Long id, @RequestParam Integer quantity) {
    	System.out.println("CartController.updateCartItem()");
        return cartService.updateCartItem(id, quantity);
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
