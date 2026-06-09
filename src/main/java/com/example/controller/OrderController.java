package com.example.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.entity.Order;
import com.example.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	    @Autowired
	    private OrderService orderService;

	    @PostMapping
	    public Order createOrder(@RequestParam Long userId) {
	        System.out.println("OrderController.createOrder()");
	        return orderService.createOrder(userId);
	    }

	    @GetMapping
	    public List<Order> getAllOrders() {
	        System.out.println("OrderController.getAllOrders()");
	        return orderService.getAllOrders();
	    }

	    @GetMapping("/{id}")
	    public Order getOrderById(@PathVariable Long id) {
	        System.out.println("OrderController.getOrderById()");
	        return orderService.getOrderById(id);
	    }

	    @PatchMapping("/{id}/cancel")
	    public Order cancelOrder(@PathVariable Long id) {
	        System.out.println("OrderController.cancelOrder()");
	        return orderService.cancelOrder(id);
	    }
}
