package com.example.project.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
@AllArgsConstructor
@Getter
public class UserFormRequestDto {
    private String userId;
    private String userPw;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserFormRequestDto that = (UserFormRequestDto) o;
        return Objects.equals(userId, that.userId) && Objects.equals(userPw, that.userPw);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userPw);
    }
}
