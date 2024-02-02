package com.example.project.controller.v1;

import com.example.project.dto.request.UserFormRequestDto;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api")
public class LoginTestController {

    @PostMapping("/login")
    public UserFormRequestDto login(@RequestBody UserFormRequestDto userFormRequestDto){

        return userFormRequestDto;
    }

}
