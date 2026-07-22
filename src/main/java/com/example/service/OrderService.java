package com.example.service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.CustomerRepository;
import com.example.dao.OrderRepository;
import com.example.dao.ProductRepository;
import com.example.dto.order.OrderRequest;
import com.example.dto.order.OrderResponse;
import com.example.dto.order.OrderUpdateRequest;
import com.example.entity.Customer;
import com.example.entity.Order;
import com.example.entity.OrderItem;
import com.example.entity.Product;


@Service
public class OrderService {

	    @Autowired
	    private OrderRepository orderRepository;

	    @Autowired
	    private CustomerRepository customerRepository;
	    
	    @Autowired
	    private ProductRepository productRepository;
	    
	    
	    private OrderResponse convertToResponse(Order order) {

	        return OrderResponse.builder()
	        		    .id(order.getId())
	        		    .orderNumber(order.getOrderNumber())
	        		    .totalAmount(order.getTotalAmount())
	        		    .shippingAddress(order.getShippingAddress())
	        		    .paymentMethod(order.getPaymentMethod())
	        		    .paymentStatus(order.getPaymentStatus())
	        		    .deliveryStatus(order.getDeliveryStatus())
	        		    .status(order.getStatus())
	                .active(order.getActive())
	                .createdAt(order.getCreatedAt())
	                .updatedAt(order.getUpdatedAt())
	                .customerId(order.getCustomer().getCustomerId())       
	                .customerFirstName(order.getCustomer().getFirstName())
	                .customerLastName(order.getCustomer().getLastName())
	                .phoneNumber(order.getCustomer().getPhoneNumber())
	                .orderItems(order.getOrderItems())
	                .deliveredAt(order.getDeliveredAt())
	                .build();
	    }

	    public OrderResponse createOrder(OrderRequest request) {
	        System.out.println("OrderService.createOrder()");
	        Customer customer = customerRepository.findById(request.getCustomerId())
	                .orElseThrow(() ->
	                        new RuntimeException("User not found"));
	        
	        
	        Order order =Order.builder()
	        		    .customer(customer)
	        		    .orderNumber("ORD-"+System.currentTimeMillis())
	        		    .totalAmount(BigDecimal.ZERO)
	        		    .status("PLACED")
	        	        .shippingAddress(request.getShippingAddress())
	        	        .paymentMethod(request.getPaymentMethod())
	        	        .paymentStatus("PENDING")
	        	        .deliveryStatus("PROCESSING")
	        	        .deliveredAt(null)
	        	        .createdAt(LocalDateTime.now())
	        	        .active(true)
	        	        .build();
	       
	        order= orderRepository.save(order);
	        
	        for (OrderItem item : order.getOrderItems()) {
	            Product product = item.getProduct();
	            product.setTotalSold(
	                    product.getTotalSold() + item.getQuantity()
	            );
	            productRepository.save(product);
	        }
	        return convertToResponse(order);
	    }

	    public List<OrderResponse> getAllOrders() {
	        System.out.println("OrderService.getAllOrders()");
	        return orderRepository.findAll()
	        		                  .stream()
		                          .map(this::convertToResponse)
		                          .toList();
	    }

	    public OrderResponse getOrderById(Long id) {
	        System.out.println("OrderService.getOrderById()");
	        Order order= orderRepository.findById(id)
	                .orElseThrow(() ->
	                        new RuntimeException("Order not found"));
	        return convertToResponse(order);
	    }

	    public OrderResponse cancelOrder(Long id) {
	        System.out.println("OrderService.cancelOrder()");
	        Order order = orderRepository.findById(id)
	                .orElseThrow(() ->
	                        new RuntimeException("Order not found"));
	        order.setDeliveryStatus("CANCELLED");
	        order.setUpdatedAt(LocalDateTime.now());
	        order= orderRepository.save(order);
	        return convertToResponse(order);
	    }
	    
	    public OrderResponse updateOrder(long id,OrderUpdateRequest request) {
	        System.out.println("OrderService.updateOrder()");
	    	      Order order= orderRepository.findById(id)
		                .orElseThrow(() ->
		                        new RuntimeException("Order not found"));

	    	      order.setShippingAddress(request.getShippingAddress());
	    	      order.setPaymentStatus(request.getPaymentStatus());
	    	      order.setDeliveryStatus(request.getDeliveryStatus());
	    	      order.setUpdatedAt(LocalDateTime.now());
	    	      
	    	      order= orderRepository.save(order);
	  	      return convertToResponse(order);
	    	
	    }
	    
	    
	    public OrderResponse deliverOrder(Long orderId) {

	        Order order = orderRepository.findById(orderId)
	                .orElseThrow(() ->
	                        new RuntimeException("Order not found."));

	        order.setDeliveryStatus("DELIVERED");
	        order.setDeliveredAt(LocalDateTime.now());
	        order.setUpdatedAt(LocalDateTime.now());

	        order= orderRepository.save(order);
	  	      return convertToResponse(order);
	    }
	
}
