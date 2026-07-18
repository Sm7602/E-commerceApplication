package com.example.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.dao.UserRepository;
import com.example.entity.User;

@Service
public class CustomerService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User saveUser(User user) {
		System.out.println("CustomerService.saveUser()");
		userRepository.save(user);
		return user;
	}
	
	public List<User> getAllUser() {
		System.out.println("CustomerService.getAllUser()");
		return userRepository.findAll();
	}
	
	public User getUserById(long id) {
		System.out.println("CustomerService.getUserById() running.......");
		return userRepository.findById(id).orElseThrow(() ->
        new RuntimeException("User not found"));
	}
	
	public User  updateUser(long id,User updatedUser) {
		System.out.println("CustomerService.updateuser() running.......");
		 User existingUser = userRepository.findById(id)
	                .orElseThrow(() ->
	                        new RuntimeException("User not found with id: " + id));

	        existingUser.setFirstname(updatedUser.getFirstname());
	        existingUser.setLastName(updatedUser.getLastName());
	        existingUser.setEmail(updatedUser.getEmail());
	        existingUser.setPassword(updatedUser.getPassword());
	        existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
	        existingUser.setProfileImage(updatedUser.getProfileImage());

	        return userRepository.save(existingUser);
	}
		
	public void deleteUserById(long id) {
		System.out.println("CustomerService.deleteUserById() running.......");
		 User user = userRepository.findById(id)
	                .orElseThrow(() ->
	                        new RuntimeException("User not found with id: " + id));
		userRepository.delete(user);
	}
	
	public void deleteallUser() {
		System.out.println("CustomerService.deleteallUser() running.......");
		userRepository.deleteAll();
	}
	
	

}
