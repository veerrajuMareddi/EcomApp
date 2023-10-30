package com.udacity.ecom.service;

import com.udacity.ecom.entity.UserInfo;

public interface UserService {
    UserInfo createUser(UserInfo user);
    UserInfo getUserById(Long userId);
    UserInfo findByUsername(String username);
}
