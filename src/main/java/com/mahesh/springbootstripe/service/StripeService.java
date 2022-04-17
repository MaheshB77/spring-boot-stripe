package com.mahesh.springbootstripe.service;

import com.mahesh.springbootstripe.model.User;

import java.util.List;

public interface StripeService {
    User createUser(User user);
    List<User> getAllUsers();
}
