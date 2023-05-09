package com.kevinsdj.traditionalrest.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
public class SayHelloService {

    @Async
    public CompletableFuture<String> sayHelloAsync(){
        
        return CompletableFuture.completedFuture("async response");
    }
    
    
    public String sayHello(){
        
        return "normal response";
    }
    
}
