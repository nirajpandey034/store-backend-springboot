package storeBackend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import storeBackend.entity.User;
import storeBackend.services.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/getAll")
	public List<User> getAllUsers() {
		return userService.getAll();
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<?> getUser(@PathVariable("id") int id) {
		User user = userService.getUser(id);

		if (user != null) {
			return ResponseEntity.ok(user);
		} else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"User not found\"}");
	}

	@PostMapping("/add")
	public User addUser(@RequestBody User req) {
		return userService.addUser(req);
	}

	@PutMapping("/update")
	public ResponseEntity<?> updateUser(@RequestBody Map<String, String> req) {
		int status = userService.updateUserName(req);
		if (status == 0)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok("User phone Updated");
	}

	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteUser(@RequestBody Map<String, Integer> req) {
		return userService.deleteUser(req);
	}

}
