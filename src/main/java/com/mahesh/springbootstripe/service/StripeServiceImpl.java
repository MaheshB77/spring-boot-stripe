package com.mahesh.springbootstripe.service;

import com.mahesh.springbootstripe.model.User;
import com.stripe.Stripe;
import com.stripe.model.Customer;
import com.stripe.model.CustomerCollection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class StripeServiceImpl implements StripeService{

    @Value("${stripe.api.key}")
    private String STRIPE_API_KEY;

    @Override
    public User createUser(User user) {
        try {
            Stripe.apiKey = STRIPE_API_KEY;
            Map<String, Object> params = new HashMap<>();
            params.put("name", user.getName());
            params.put("email", user.getEmail());
            Customer customer = Customer.create(params);
            user.setStripeId(customer.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        try {
            Stripe.apiKey = STRIPE_API_KEY;
            Map<String, Object> params = new HashMap<>();
            params.put("limit", 1000);
            CustomerCollection customerCollection = Customer.list(params);
            List<User> users = new ArrayList<>();
            for (int i = 0; i < customerCollection.getData().size(); i++) {
                User user = new User();
                user.setStripeId(customerCollection.getData().get(i).getId());
                user.setName(customerCollection.getData().get(i).getName());
                user.setEmail(customerCollection.getData().get(i).getEmail());
                user.setId(ThreadLocalRandom.current().nextLong(1, 1000000000));
                users.add(user);
            }
            return users;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
