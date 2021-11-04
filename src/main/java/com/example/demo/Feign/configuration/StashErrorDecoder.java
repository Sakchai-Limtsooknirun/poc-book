//package com.example.demo.Feign.configuration;
//
//import feign.Response;
//import feign.codec.ErrorDecoder;
//
//import static feign.FeignException.errorStatus;
//
//public class StashErrorDecoder implements ErrorDecoder {
//
//    @Override
//    public Exception decode(String methodKey, Response response) {
//        if (response.status() >= 400 && response.status() <= 499) {
//            return new Exception("Error:" + response.status() + "-->" + response.body());
//        }
//        if (response.status() >= 500 && response.status() <= 599) {
//            return new Exception("Error:" + response.status() + "-->" + response.body());
//        }
//        return errorStatus(methodKey, response);
//    }
//}
