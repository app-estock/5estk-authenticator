package com.market.authenticator.service;

import java.util.Optional;

import com.market.authenticator.exception.UserAlreadyExistsException;
import com.market.authenticator.exception.UserNotFoundException;
import com.market.authenticator.model.User;
import com.market.authenticator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImplementation implements UserService {

	
	@Autowired
	private final UserRepository userRepo;

	public UserServiceImplementation(UserRepository userRepo) {
		super();
		this.userRepo = userRepo;
	}

	@Override
	public boolean saveUser(User user) throws UserAlreadyExistsException {

		Optional<User> u1 = userRepo.findById(user.getUserId());
		if (u1.isPresent()) {
			throw new UserAlreadyExistsException("UserId already exists");
		}
		userRepo.save(user);

		return true;
	}

	@Override
	public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException {

		User user = userRepo.findByUserIdAndPassword(userId, password);

		if (user == null) {
			throw new UserNotFoundException("UserId or Password mismatch");
		}
		return user;
	}

}