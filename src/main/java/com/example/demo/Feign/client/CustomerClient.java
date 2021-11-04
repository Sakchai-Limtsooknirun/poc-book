package com.example.demo.Feign.client;

import com.example.demo.Feign.configuration.FeignTestConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "customerService",
        url = "http://localhost:8011",
        configuration = FeignTestConfiguration.class)

public interface CustomerClient {
    @RequestMapping(method = RequestMethod.GET, path = "/v1/pointX/eapi/customerAddress/{rmId}", consumes = "application/json")
    String getCustomer(@PathVariable("rmId") String ployId);

}
