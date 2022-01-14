package com.jmurphey.exam.services;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jmurphey.exam.models.User;
import com.jmurphey.exam.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	
// --- Register user and hash their Password ---
	
	public User registerUser(User user) {
		String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hashed);
		return userRepo.save(user);
		}
	
	
// --- Authenticate User ---
	
	public boolean authenticateUser(String email, String password) {
		
		User user = userRepo.findByEmail(email);
		
		if(user == null) {
			return false;
		} else {
			if(BCrypt.checkpw(password, user.getPassword())) {
				return true;
			} else {
				return false;
			}
		}
	}

	
// --- Update/Delete User --- 
	
	public User updateUser(User user) {
		return userRepo.save(user);
	}
	
	public void deleteUser(Long id) {
		userRepo.deleteById(id);
	}
	
	
// --- Find User by Email ---
	
	public User findByEmail(String email) {
		return userRepo.findByEmail(email);
	}
	
	
// --- Find User by Id ---
	
	public User findUserById(Long id) {
		return this.userRepo.findById(id).orElse(null);
	}
	

// --- Find All Users ---
	
	public List<User> allUsers(){
		return userRepo.findAll();
	}

	
// --- Create/Add-to Relationship ---
	

}
