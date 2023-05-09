package com.kevinsdj.traditionalrest.rest;

import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kevinsdj.traditionalrest.service.SayHelloService;


@RestController
public class SayHello {
    
    @Autowired
    private SayHelloService service;

    
    @GetMapping("/say-hello-async")
    public CompletableFuture<String> sayHelloAsync(){

        return service.sayHelloAsync();
    }
    @GetMapping("/say-hello")
    public String sayHello(){
        return service.sayHello();
    }
}
