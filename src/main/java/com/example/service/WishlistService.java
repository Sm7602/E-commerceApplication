package com.example.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.dao.ProductRepository;
import com.example.dao.UserRepository;
import com.example.dao.WishlistRepository;
import com.example.entity.Product;
import com.example.entity.User;
import com.example.entity.Wishlist;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public Wishlist addToWishlist(Long userId, Long productId) {
    	System.out.println("WishlistService.addToWishlist()");
        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));
        Wishlist wishlist = new Wishlist();
        wishlist.setUser(user);
        wishlist.setProduct(product);
        return wishlistRepository.save(wishlist);
    }

    public List<Wishlist> getWishlist(Long userId) {
    	System.out.println("WishlistService.getWishlist()");
        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
        return wishlistRepository.findByUser(user);
    }

    public void removeFromWishlist(Long userId, Long productId) {
        System.out.println("WishlistService.removeFromWishlist()");
        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));
        Wishlist wishlist = wishlistRepository.findByUserAndProduct(user, product).orElseThrow(() ->
                        new RuntimeException("Wishlist item not found"));
        wishlistRepository.delete(wishlist);
    }
}