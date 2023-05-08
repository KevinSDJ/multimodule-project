package com.mycompany.ssedemo.utils;

import java.util.ArrayList;
import java.util.Collection;

import com.mycompany.ssedemo.dto.Message;


public class FakeDb {
    private static FakeDb instance;
    Collection<Message> messages;

    private FakeDb(){
        this.messages= new ArrayList<>();
    }

    public static FakeDb getInstance(){
        return instance; 
    }

    public void addMessages(Message message) throws Exception{
        if(!messages.contains(message)){
            messages.add(message);
            return;
        }
        throw new Exception("message already exist");
        
    }
    public void removeMessages(Message message)throws Exception{
        if(!messages.contains(message)){
            throw new Exception("message could delete, because not exist");
        }
        messages.remove(message);
    }

    public Collection<Message> geMessages(){
        return messages;
    }

}
