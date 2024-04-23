package storeBackend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import storeBackend.dao.UserRepository;
import storeBackend.entity.User;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public List<User> getAll() {
		List<User> list=new ArrayList<User>();
		Iterable<User> iterable = userRepository.findAll();
		for(User user : iterable) list.add(user);
		return list;
	}
	
	public User getUser(int id) {
		try {
			User user = userRepository.findById(id).get();
			return user;
		} catch (Exception e) {
			return null;
		}
	}
	
	public User addUser(User user) {
		return userRepository.save(user);
	}
	
	public int updateUserName(Map<String, String> req) {
		int id = Integer.parseInt(req.get("id"));
		String phone = req.get("phone");
		return userRepository.changeUserPhone(id, phone);
	}
	
	public ResponseEntity<?> deleteUser(Map<String, Integer> req) {
		Integer id = req.get("id");
		if (id == null) {
			return ResponseEntity.badRequest().body("ID is required in the request");
		}

		if (userRepository.existsById(id)) {
			userRepository.deleteById(id);
			return ResponseEntity.ok("User deleted successfully");
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
