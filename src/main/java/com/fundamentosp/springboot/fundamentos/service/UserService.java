package com.fundamentosp.springboot.fundamentos.service;

import com.fundamentosp.springboot.fundamentos.entity.User;
import com.fundamentosp.springboot.fundamentos.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {
    private final Log LOG = LogFactory.getLog(UserService.class);
    private static UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

	@Transactional
    public void saveTransactional(List<User> users){
        users.stream()
                .peek(user -> LOG.info("Saving user: " + user))
                .forEach(userRepository::save);
    }
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

	public static User save(User newUser) {
		return userRepository.save(newUser);
	}

	public void delete(Long id) {
		userRepository.delete(new User(id));
	}

	public User update(Long id, User newUser) {
		 return
				 userRepository.findById(id)
				.map(
						user -> {
							user.setName(newUser.getName());
							user.setEmail(newUser.getEmail());
							user.setName(newUser.getName());
							return userRepository.save(user);
						}
				).get();
	}
}
