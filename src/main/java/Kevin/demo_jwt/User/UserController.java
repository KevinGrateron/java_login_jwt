package Kevin.demo_jwt.User;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200"})

public class UserController {
	private final UserService userService;
	
	@GetMapping("{id}")
	public ResponseEntity<UserDTO> getUser(@PathVariable Integer id){
		UserDTO userDTO = userService.getUser(id);
		if(userDTO == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(userDTO);
	}
	@GetMapping()
	public ResponseEntity<List<UserDTO>> getAllUsers(){
		try {
			List<UserDTO> users = userService.getAllUsers();
			if(users.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(users);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	
	}
	
	@PutMapping()
	public ResponseEntity<UserResponse> updateUser(@RequestBody UserRequest userRequest){
		return ResponseEntity.ok(userService.updateUser(userRequest));
	}
	
}
