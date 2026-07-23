package com.example.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.dto.order.OrderRequest;
import com.example.dto.order.OrderResponse;
import com.example.dto.order.OrderUpdateRequest;
import com.example.service.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

	    @Autowired
	    private OrderService orderService;

	    @PostMapping
	    public OrderResponse createOrder(@Valid @RequestBody OrderRequest request) {
	        System.out.println("OrderController.createOrder()");
	        return orderService.createOrder(request);
	    }

	    @GetMapping
	    public List<OrderResponse> getAllOrders() {
	        System.out.println("OrderController.getAllOrders()");
	        return orderService.getAllOrders();
	    }

	    @GetMapping("/{id}")
	    public OrderResponse getOrderById(@PathVariable Long id) {
	        System.out.println("OrderController.getOrderById()");
	        return orderService.getOrderById(id);
	    }

	    @PatchMapping("/{id}/cancel")
	    public OrderResponse cancelOrder(@PathVariable Long id) {
	        System.out.println("OrderController.cancelOrder()");
	        return orderService.cancelOrder(id);
	    }
	    
	    @PutMapping("/{id}")
	    public OrderResponse updateOrder(@PathVariable long id,@Valid @RequestBody OrderUpdateRequest request) {
	        	System.out.println("OrderController.updateOrder()");
	        return orderService.updateOrder(id, request);
	    }
	    
	    @PatchMapping("/{id}/deliver")
	    public OrderResponse deliverOrder(@PathVariable Long orderId) {
	    	    System.out.println("OrderController.deliverOrder()");
	        return orderService.deliverOrder(orderId);
	    }
}
