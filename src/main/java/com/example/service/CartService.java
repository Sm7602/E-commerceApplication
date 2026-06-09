package com.example.service;
import java.math.BigDecimal;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.dao.CartItemRepository;
import com.example.dao.CartRepository;
import com.example.dao.ProductRepository;
import com.example.dao.UserRepository;
import com.example.entity.Cart;
import com.example.entity.CartItem;
import com.example.entity.Product;
import com.example.entity.User;

@Service
public class CartService {
	  @Autowired
	    private UserRepository userRepository;

	    @Autowired
	    private ProductRepository productRepository;

	    @Autowired
	    private CartRepository cartRepository;

	    @Autowired
	    private CartItemRepository cartItemRepository;


	    public Cart addItemToCart(Long userId, Long productId,Integer quantity) {
	    	System.out.println("CartService.addItemToCart()");
	        User user = userRepository.findById(userId)
	                .orElseThrow(() ->
	                        new RuntimeException("User not found"));

	        Product product = productRepository.findById(productId)
	                .orElseThrow(() ->
	                        new RuntimeException("Product not found"));

	        
	        Cart cart = user.getCart();
	        if (cart == null) {
	            cart = new Cart();
	            cart.setUser(user);
	            cart.setItems(new ArrayList<>());
	            cart.setTotalAmount(BigDecimal.ZERO);
	            cart = cartRepository.save(cart);
	            user.setCart(cart);
	            userRepository.save(user);
	        }

	       
	        CartItem existingItem = cart.getItems().stream().filter(item ->
	                        item.getProduct().getId() == productId).findFirst() .orElse(null);

	        if (existingItem != null) {
	            existingItem.setQuantity( existingItem.getQuantity() + quantity);
	            cartItemRepository.save(existingItem);

	        } else {
	            CartItem cartItem = new CartItem();
	            cartItem.setCart(cart);
	            cartItem.setProduct(product);
	            cartItem.setQuantity(quantity);
	            cartItemRepository.save(cartItem);
	            cart.getItems().add(cartItem);
	        }
	        BigDecimal totalAmount = cart.getItems().stream().map(item ->
	                        item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
	                .reduce(BigDecimal.ZERO, BigDecimal::add);
	        cart.setTotalAmount(totalAmount);

	        return cartRepository.save(cart);
	    }


	    public Cart getCart(Long userId) {
	    	System.out.println("CartService.getCart()");
	        User user = userRepository.findById(userId)
	                .orElseThrow(() ->
	                        new RuntimeException("User not found"));
	        Cart cart = user.getCart();
	        if (cart == null) {
	            cart = new Cart();
	            cart.setUser(user);
	            cart.setItems(new ArrayList<>());
	            cart.setTotalAmount(BigDecimal.ZERO);
	            cart = cartRepository.save(cart);
	            user.setCart(cart);
	            userRepository.save(user);
	        }
	        return cart;
	    }

	  
	    public Cart updateCartItem(Long cartItemId,Integer quantity) {
	    	System.out.println("CartService.updateCartItem()");
	        CartItem cartItem = cartItemRepository.findById(cartItemId)
	                .orElseThrow(() ->
	                        new RuntimeException("Cart Item not found"));
	        cartItem.setQuantity(quantity);
	        cartItemRepository.save(cartItem);
	        return cartItem.getCart();
	    }

	  
	    public void removeCartItem(Long cartItemId) {
	    	System.out.println("CartService.removeCartItem()");
	        CartItem cartItem = cartItemRepository.findById(cartItemId)
	                .orElseThrow(() ->
	                        new RuntimeException("Cart Item not found"));
	        cartItemRepository.delete(cartItem);
	    }

	    
	    public void clearCart(Long userId) {
	    	System.out.println("CartService.clearCart()");
	        User user = userRepository.findById(userId)
	                .orElseThrow(() ->
	                        new RuntimeException("User not found"));
	        Cart cart = user.getCart();
	        if (cart == null) {
	            throw new RuntimeException("Cart not found");
	        }
	        if (cart.getItems() != null && !cart.getItems().isEmpty()) {
	            cartItemRepository.deleteAll(cart.getItems());
	        }
	        cart.setTotalAmount(BigDecimal.ZERO);
	        cartRepository.save(cart);
	    }

}
