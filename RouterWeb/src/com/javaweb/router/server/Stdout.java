package com.javaweb.router.server;

import com.javaweb.router.bean.Message;
import version.one.util.TestUtilGP;

import java.util.ArrayList;
import java.util.List;

public class Stdout {
    public List<Message> getMessage(String key) {
        List<Message> messages = new ArrayList<>();
        int i = 1;
        for(String string: TestUtilGP.getRedis(key)) {
            Message message = new Message(i,string);
            messages.add(message);
            i++;
        }
        return messages;
    }
}
