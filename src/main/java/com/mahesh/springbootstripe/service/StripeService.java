package com.mahesh.springbootstripe.service;

import com.mahesh.springbootstripe.model.User;

public interface StripeService {
    User createUser(User user);
}
