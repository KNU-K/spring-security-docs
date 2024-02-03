package com.example.project.controller.v1;

import com.example.project.dto.request.UserFormRequestDto;
import com.example.project.service.UserService;
import com.example.project.util.SessionManger;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@Slf4j
@RequestMapping("/api/v1/auth")
public class LoginTestController {
    private final UserService userService;
    private final SessionManger sessionManger;
    @Autowired
    public LoginTestController(UserService userService, SessionManger sessionManger) {
        this.userService = userService;
        this.sessionManger = sessionManger;
    }

    @PostMapping("/login")
    public String login(@RequestBody UserFormRequestDto userFormRequestDto,HttpServletRequest req) {
        HttpSession httpSession =  req.getSession();
        if(userService.findUser(userFormRequestDto)){
            // create session;
            sessionManger.createSession(httpSession.getId(), userFormRequestDto.getUserId());

            return  "good";
        }else{
            return "fail";
        }
    }


    @GetMapping("/profile")
    public String getProfile(HttpServletRequest req){
        String sessionId =  req.getSession().getId();
        if(sessionManger.containsSession(sessionId)){
           return sessionManger.getSession(sessionId).toString();
        }else{
            return "fail";
        }
    }
}
