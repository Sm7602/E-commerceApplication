package com.example.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.entity.User;
import com.example.service.CustomerService;

@RestController
@RequestMapping("/api/users")
public class CustomerController {
	
//	@GetMapping("/user")
//	public User greetings() {
//		System.out.println("CustomerController.greetings()");
//		return new User("souvik","Maity","sm24@gmail.com","dad123","7602567154","http/image.com");
//	}
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping()
	public User requestBodyInfo(@RequestBody User user) {
		System.out.println(user+"CustomerController.requestBodyInfo()");
		customerService.saveUser(user);
		return user;	
	}
	
	@GetMapping()
	public List<User> getAllUser() {
		System.out.println("CustomerController.getAllUser()");
		return customerService.getAllUser();
	}
	
	@GetMapping("/{id}")
	public User getUserById(@PathVariable long id) {
		System.out.println("CustomerController.getUserById() running.......");
		return customerService.getUserById(id);
	}
	
	@PutMapping("/{id}")
	public User updateUser(@PathVariable Long id,@RequestBody User user) {
		System.out.println("CustomerController.updateUser() running.......");
		return customerService.updateUser(id,user);
	}
	
	@DeleteMapping("/{id}")
	public void deleteUserById(@PathVariable long id) {
		System.out.println("CustomerController.deleteUserById() running.......");
		customerService.deleteUserById(id);
	}
	
	@DeleteMapping("/deleteAllUsers")
	public void deleteallUser() {
		System.out.println("CustomerController.deleteAllUser() running.......");
		customerService.deleteallUser();
	}
	

}
