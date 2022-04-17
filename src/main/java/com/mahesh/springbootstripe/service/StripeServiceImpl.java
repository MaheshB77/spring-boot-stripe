package com.mahesh.springbootstripe.service;

import com.mahesh.springbootstripe.model.User;
import com.stripe.Stripe;
import com.stripe.model.Customer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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
}
