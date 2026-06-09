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
import com.example.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
//	@GetMapping("/user")
//	public User greetings() {
//		System.out.println("UserController.greetings()");
//		return new User("souvik","Maity","sm24@gmail.com","dad123","7602567154","http/image.com");
//	}
	
	@Autowired
	private UserService userService;
	
	@PostMapping()
	public User requestBodyInfo(@RequestBody User user) {
		System.out.println(user+"UserController.requestBodyInfo()");
		userService.saveUser(user);
		return user;	
	}
	
	@GetMapping()
	public List<User> getAllUser() {
		System.out.println("UserController.getAllUser()");
		return userService.getAllUser();
	}
	
	@GetMapping("/{id}")
	public User getUserById(@PathVariable long id) {
		System.out.println("UserController.getUserById() running.......");
		return userService.getUserById(id);
	}
	
	@PutMapping("/{id}")
	public User updateUser(@PathVariable Long id,@RequestBody User user) {
		System.out.println("UserController.updateUser() running.......");
		return userService.updateUser(id,user);
	}
	
	@DeleteMapping("/{id}")
	public void deleteUserById(@PathVariable long id) {
		System.out.println("UserController.deleteUserById() running.......");
		userService.deleteUserById(id);
	}
	
	@DeleteMapping("/deleteAllUsers")
	public void deleteallUser() {
		System.out.println("UserController.deleteAllUser() running.......");
		userService.deleteallUser();
	}
	

}
