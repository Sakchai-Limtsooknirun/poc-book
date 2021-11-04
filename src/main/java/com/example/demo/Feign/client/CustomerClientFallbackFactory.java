//package com.example.demo.Feign.client;
//
//
//import feign.FeignException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.cloud.openfeign.FallbackFactory;
//import org.springframework.stereotype.Component;
//
//@Component
//class CustomerClientFallbackFactory implements FallbackFactory<CustomerClient> {
//
//    private final Logger log = LoggerFactory.getLogger(this.getClass());
//
//    @Override
//    public CustomerClient create(Throwable cause) {
//
//        String httpStatus = cause instanceof FeignException ? Integer.toString(((FeignException) cause).status()) : "";
//
//        return new CustomerClient() {
//            @Override
//            public String getCustomer(String ployId) throws Exception {
//                log.error(httpStatus);
//                System.out.println("xxxxxxxxwswdflwofklwpokfopwef");
//                throw new Exception(cause);
//            }
//        };
//    }
//}
