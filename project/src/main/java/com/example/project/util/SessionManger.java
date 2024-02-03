package com.example.project.util;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SessionManger {
    private final Map<String, String> sessions = new HashMap<>();
    public void createSession(String sessionId, String data){
        sessions.put(sessionId, data);
    }
    public String getSession(String sessionId){
        return sessions.get(sessionId);
    }
    public void removeSession(String sessionId){
        sessions.remove(sessionId);
    }
    public boolean containsSession(String sessionId){
        return sessions.containsKey(sessionId);
    }


}
