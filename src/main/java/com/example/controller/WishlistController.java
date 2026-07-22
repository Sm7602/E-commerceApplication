package com.example.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.dto.wishlist.WishlistRequest;
import com.example.dto.wishlist.WishlistResponse;
import com.example.service.WishlistService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @PostMapping("/{productId}")
    public WishlistResponse addToWishlist(@Valid @RequestBody WishlistRequest request) {
        System.out.println("WishlistController.addToWishlist()");
        return wishlistService.addToWishlist(request);
    }

    @GetMapping
    public List<WishlistResponse> getWishlist( @RequestParam Long userId) {
        System.out.println("WishlistController.getWishlist()");
        return wishlistService.getWishlist(userId);
    }

    @DeleteMapping("/{productId}")
    public String removeFromWishlist(@Valid @RequestBody WishlistRequest request) {
        System.out.println("WishlistController.removeFromWishlist()");
        wishlistService.removeFromWishlist(request);
        return "Product removed from wishlist";
    }
}