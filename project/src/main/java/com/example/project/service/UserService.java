package com.example.project.service;

import com.example.project.domain.User;
import com.example.project.dto.request.UserFormRequestDto;
import com.example.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public boolean findUser(UserFormRequestDto userFormRequestDto){
        Optional<User> user = userRepository.findByUserId(userFormRequestDto.getUserId());
        if(user.isEmpty())// error;
            return false;
        String userPw = user.stream().findFirst().orElse(null).getUserPw();
        if(userFormRequestDto.getUserPw().equals(userPw)) return true;
        return false;
    }

}
