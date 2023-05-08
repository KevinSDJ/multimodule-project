package com.mycompany.ssedemo.inputPort;

import com.mycompany.ssedemo.dto.Message;
import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Async
public class News {

    private final Logger LOGGER= LoggerFactory.getLogger(getClass());
    private List<SseEmitter> emitters= new CopyOnWriteArrayList<>();
    
    @CrossOrigin("*")
    @GetMapping(value="/",produces =MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribe(){

        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        emitter.onCompletion(()->{
            emitter.complete();
            if(emitters.contains(emitter)){
                emitters.remove(emitter);
            }
        });
        emitter.onError((error)->{
            emitter.completeWithError(error);
            if(emitters.contains(emitter)){
                emitters.remove(emitter);
            }
        });
        emitter.onTimeout(()-> {
            emitter.complete();
            if(emitters.contains(emitter)){
                emitters.remove(emitter);
            }
        });
        emitters.add(emitter);
        return emitter;
    }

    @PostMapping("/")
    public void postNews(@PathParam("message") String message){
        Message messg= new Message(message,Date.from(Instant.now()));

        for(SseEmitter e:emitters){
            try {
                e.send(SseEmitter.event()
                .data(messg,MediaType.APPLICATION_JSON)
                .name("message-news")
                .reconnectTime(40000)
                .id(UUID.randomUUID().toString())
                .build());   
            } catch (IOException error) {
                e.completeWithError(error);
            }
        }
    }
}
