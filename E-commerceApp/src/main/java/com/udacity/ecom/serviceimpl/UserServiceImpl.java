package com.udacity.ecom.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.ecom.entity.UserInfo;
import com.udacity.ecom.repo.UserRepository;
import com.udacity.ecom.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserInfo createUser(UserInfo user) {
        return userRepository.save(user);
    }

    @Override
    public UserInfo getUserById(Long userId) {
        // Implement logic to retrieve a user by their ID
        return userRepository.findById(userId).orElse(null);
    }

	@Override
	public UserInfo findByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(username);
	}

}
