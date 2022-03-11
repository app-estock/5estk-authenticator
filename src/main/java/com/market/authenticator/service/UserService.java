package com.market.authenticator.service;
import com.market.authenticator.exception.UserAlreadyExistsException;
import com.market.authenticator.exception.UserNotFoundException;
import com.market.authenticator.model.User;

public interface UserService {

	boolean saveUser(User user) throws UserAlreadyExistsException, UserNotFoundException;

	public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException;


}