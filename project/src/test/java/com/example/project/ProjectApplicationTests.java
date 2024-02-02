package com.example.project;

import com.example.project.domain.User;
import com.example.project.dto.request.UserFormRequestDto;
import com.example.project.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
class ProjectApplicationTests {

    @Autowired
    private UserRepository userRepository;
    @Transactional
    @Test
    @DisplayName("create user test")
    void createUserTest(){
        //index 단수 indices
        String userId="asd1", userPw="asd2";
        UserFormRequestDto user = new UserFormRequestDto(userId,userPw);
        User newUser = new User(user.getUserId(), user.getUserPw());
        userRepository.saveAndFlush(newUser);
        List<User> users= userRepository.findAll();
        assertThat(users).hasSize(2);
        User savedUser = users.get(0);
        assertThat(savedUser.getUserId()).isEqualTo(userId);
        assertThat(savedUser.getUserPw()).isEqualTo(userPw);
    }
    @Transactional
    @Test
    @DisplayName("read user test")
    void readUserTest(){
        //index 단수 indices
        String userId="asd1", userPw="asd2";
        List<User> users= userRepository.findAll();
        assertThat(users).hasSize(1);
        User savedUser = users.get(0);
        assertThat(savedUser.getUserId()).isEqualTo(userId);
        assertThat(savedUser.getUserPw()).isEqualTo(userPw);
    }
    @Transactional
    @Test
    @DisplayName("update user test")
    void updateUserTest() {
        String userId = "asd1";
        String updatedUserPw = "newPassword";
        List<User> users = userRepository.findAll();
        assertThat(users).hasSize(1);

        User savedUser = users.get(0);

        savedUser.setUserPw(updatedUserPw);
        userRepository.saveAndFlush(savedUser);

        List<User> updatedUsers = userRepository.findAll();
        assertThat(updatedUsers).hasSize(1);

        User updatedUser = updatedUsers.get(0);

        assertThat(updatedUser.getUserId()).isEqualTo(userId);
        assertThat(updatedUser.getUserPw()).isEqualTo(updatedUserPw);
    }

    @Transactional
    @Test
    @DisplayName("delete user test")
    void deleteUserTest() {
        // Given
        String userId = "asd4";
        String userPw = "asd4";

        // Save a new user
        User newUser = new User(userId, userPw);
        userRepository.saveAndFlush(newUser);

        // When
        // Retrieve the user from the database
        List<User> users = userRepository.findAll();
        assertThat(users).hasSize(2);

        User savedUser = users.get(0);

        // Delete the user
        userRepository.delete(savedUser);

        // Then
        // Retrieve the users again to verify deletion
        List<User> remainingUsers = userRepository.findAll();
        assertThat(remainingUsers).hasSize(1);
    }
    @Test
    @DisplayName("test")
    void dtoTest() {
        String userId="asd1", userPw="asd2";
        UserFormRequestDto user = new UserFormRequestDto(userId,userPw);
        assertThat(user.getUserId()).isEqualTo("asd1");
        assertThat(user.getUserPw()).isEqualTo("asd2");


    }

}
