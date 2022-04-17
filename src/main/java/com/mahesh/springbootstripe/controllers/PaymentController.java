package com.mahesh.springbootstripe.controllers;

import com.mahesh.springbootstripe.model.User;
import com.mahesh.springbootstripe.service.StripeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class PaymentController {

    @Autowired
    private StripeService stripeService;

    @GetMapping("/")
    public Object home() {
        return "Home";
    }

    @PostMapping("/createUser")
    public Object createUser(@RequestBody User user) {
        try {
            return stripeService.createUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
