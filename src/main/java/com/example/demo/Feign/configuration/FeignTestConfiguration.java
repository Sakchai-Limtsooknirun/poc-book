package com.example.demo.Feign.configuration;

import feign.Feign;
import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignTestConfiguration {

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

//    @Bean
//    Feign feign() {
//        return Feign.builder()
//                .errorDecoder(new StashErrorDecoder())
//                .target(StashApi.class, url);
//    }

//    @Bean
//    public Contract feignContract() {
//        return new feign.Contract.Default();
//    }

//    @Bean
//    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
//        return new BasicAuthRequestInterceptor("user", "password");
//    }
}
