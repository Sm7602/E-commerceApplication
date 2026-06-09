package com.example.service;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.dao.OrderRepository;
import com.example.dao.UserRepository;
import com.example.entity.Order;
import com.example.entity.User;

@Service
public class OrderService {

	    @Autowired
	    private OrderRepository orderRepository;

	    @Autowired
	    private UserRepository userRepository;

	    public Order createOrder(Long userId) {
	        System.out.println("OrderService.createOrder()");
	        User user = userRepository.findById(userId)
	                .orElseThrow(() ->
	                        new RuntimeException("User not found"));
	        Order order = new Order();
	        order.setUser(user);
	        order.setOrderNumber("ORD-" + System.currentTimeMillis());
	        order.setStatus("PLACED");
	        order.setTotalAmount(BigDecimal.ZERO);
	        order.setShippingAddress("Default Address");
	        return orderRepository.save(order);
	    }

	    public List<Order> getAllOrders() {
	        System.out.println("OrderService.getAllOrders()");
	        return orderRepository.findAll();
	    }

	    public Order getOrderById(Long id) {
	        System.out.println("OrderService.getOrderById()");
	        return orderRepository.findById(id)
	                .orElseThrow(() ->
	                        new RuntimeException("Order not found"));
	    }

	    public Order cancelOrder(Long id) {
	        System.out.println("OrderService.cancelOrder()");
	        Order order = orderRepository.findById(id)
	                .orElseThrow(() ->
	                        new RuntimeException("Order not found"));
	        order.setStatus("CANCELLED");
	        return orderRepository.save(order);
	    }
	
}
