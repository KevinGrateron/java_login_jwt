package Kevin.demo_jwt.User;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	
	@Transactional
	public UserResponse updateUser(UserRequest userRequest) {
		User user = User.builder()
				.id(userRequest.id)
				.firstname(userRequest.getFirstname())
				.lastname(userRequest.lastname)
				.country(userRequest.getCountry())
				.role(Role.USER)
				.build();
		
				userRepository.updateUser(user.id, user.firstname, user.lastname, user.country);
		
				return new UserResponse("El usuario se registro satisfastoriamente");
	}
	
	public UserDTO getUser(Integer id) {
		User user = userRepository.findById(id).orElse(null);
		
		if(user != null) {
			UserDTO userDTO = UserDTO.builder()
					.id(user.id)
					.username(user.username)
					.firstname(user.firstname)
					.lastname(user.lastname)
					.country(user.country)
					.build();
			
			return userDTO;
		}
		return null;
	}
	public List<UserDTO> getAllUsers() {
		List<User> users = userRepository.findAll();
			return users.stream()
				.map(user -> new UserDTO(
						user.getId(),
						user.getUsername(),
						user.getFirstname(),
						user.getLastname(),
						user.getCountry()
						))
				.collect(Collectors.toList());
		
		
	}

}
