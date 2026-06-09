package com.example.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.entity.Wishlist;
import com.example.service.WishlistService;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @PostMapping("/{productId}")
    public Wishlist addToWishlist(@PathVariable Long productId, @RequestParam Long userId) {
        System.out.println("WishlistController.addToWishlist()");
        return wishlistService.addToWishlist(userId, productId);
    }

    @GetMapping
    public List<Wishlist> getWishlist( @RequestParam Long userId) {
        System.out.println("WishlistController.getWishlist()");
        return wishlistService.getWishlist(userId);
    }

    @DeleteMapping("/{productId}")
    public String removeFromWishlist(@PathVariable Long productId, @RequestParam Long userId) {
        System.out.println("WishlistController.removeFromWishlist()");
        wishlistService.removeFromWishlist(userId, productId);
        return "Product removed from wishlist";
    }
}