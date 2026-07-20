package com.trvelplannerplatform.user.mapper;

import org.springframework.stereotype.Component;

import com.trvelplannerplatform.user.dto.UserRegisterRequest;
import com.trvelplannerplatform.user.entity.User;

@Component
public class UserMapper {

    public User map(UserRegisterRequest request) {

        User user = new User();

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());

        return user;
    }

	
}