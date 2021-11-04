package com.example.demo.Feign.service;

import com.example.demo.Feign.client.CustomerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private CustomerClient customerClient;

    @Autowired
    public CustomerService(CustomerClient customerClient) {
        this.customerClient = customerClient;
    }

    public String getCustomer(String rmId)  {
        return customerClient.getCustomer(rmId);
    }
}
