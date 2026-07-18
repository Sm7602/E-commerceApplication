package com.example.service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.dao.CartItemRepository;
import com.example.dao.CartRepository;
import com.example.dao.CustomerRepository;
import com.example.dao.ProductRepository;
import com.example.dao.UserRepository;
import com.example.dto.admin.AdminResponse;
import com.example.dto.cart.CartRequest;
import com.example.dto.cart.CartResponse;
import com.example.entity.Admin;
import com.example.entity.Cart;
import com.example.entity.CartItem;
import com.example.entity.Customer;
import com.example.entity.Product;
import com.example.entity.User;

@Service
public class CartService {
	   
	   @Autowired
	    private CustomerRepository customerRepository;

	    @Autowired
	    private ProductRepository productRepository;

	    @Autowired
	    private CartRepository cartRepository;

	    @Autowired
	    private CartItemRepository cartItemRepository;

	    private CartResponse convertToResponse(Cart cart) {

	        return CartResponse.builder()
	                .id(cart.getId())
	                .active(cart.getActive())
	                .createdAt(cart.getCreatedAt())
	                .updatedAt(cart.getUpdatedAt())
	                .customerId(cart.getCustomer().getCustomerId())
	                .items(cart.getItems())
	                .build();
	    }
	    

	    public CartResponse addItemToCart(Long customerId, Long productId,CartRequest request) {

               System.out.println("CartService.addItemToCart()");

               Customer customer = customerRepository.findById(customerId)
                                                   .orElseThrow(() ->
                                             new RuntimeException("Customer Not Found : " + customerId));
               
              Product product = productRepository.findById(productId)
                                             .orElseThrow(() ->
                                          new RuntimeException("Product Not Found : " + productId));

                 if (product.getStockQuantity() < request.getProductId()) {
                       throw new RuntimeException("Requested quantity is not available.");
                      }

              Cart cart = customer.getCart();

           if (cart == null) {

              cart = new Cart();
              cart.setCustomer(customer);
              cart.setCreatedAt(LocalDateTime.now());
              cart.setUpdatedAt(LocalDateTime.now());
              cart.setActive(true);
              cart.setTotalAmount(BigDecimal.ZERO);
              cart.setItems(new ArrayList<>());
              cart = cartRepository.save(cart);
              
              customer.setCart(cart);

              customerRepository.save(customer);
            }


           Optional<CartItem> existingItem =cartItemRepository
        	                .findByCartAndProduct(cart,product);
           
           if(existingItem.isPresent()){
        	    CartItem item = existingItem.get();
        	    item.setQuantity(item.getQuantity()+ request.getQuantity());
        	    cartItemRepository.save(item);
         }
        	else{

        	    CartItem item = new CartItem();
        	    item.setCart(cart);
        	    item.setProduct(product);
        	    item.setQuantity(request.getQuantity());
        	    cartItemRepository.save(item);
      	}


               BigDecimal totalAmount = cart.getItems()
                                            .stream()
                                            .map(item -> item.getProduct()
                                            .getDiscountedPrice()
                                            .multiply(BigDecimal.valueOf(item.getQuantity())))
                                            .reduce(BigDecimal.ZERO,BigDecimal::add);


              cart.setTotalAmount(totalAmount);
              cart.setUpdatedAt(LocalDateTime.now());

              Cart savedCart = cartRepository.save(cart);

              return convertToResponse(savedCart);

	    }

	  
	    public CartResponse updateCartItem(Long cartItemId,Integer quantity) {
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
