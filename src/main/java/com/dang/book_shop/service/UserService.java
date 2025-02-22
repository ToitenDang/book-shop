package com.dang.book_shop.service;

import com.dang.book_shop.dto.request.UserCreationRequest;
import com.dang.book_shop.dto.response.UserResponse;
import com.dang.book_shop.entity.User;
import com.dang.book_shop.exception.AppException;
import com.dang.book_shop.exception.ErrorCode;
import com.dang.book_shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserResponse createUser(UserCreationRequest request) {
        User user = new User();

        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setFullName(request.getFullName());
        user.setDob(request.getDob());
        user.setAddress(request.getAddress());
        user.setPhone(request.getPhone());
        userRepository.save(user);

        UserResponse userResponse = new UserResponse();

        userResponse.setId(user.getId());
        userResponse.setEmail(user.getEmail());
        userResponse.setFullName(user.getFullName());
        userResponse.setDob(user.getDob());
        userResponse.setAddress(user.getAddress());
        userResponse.setPhone(user.getPhone());
        return userResponse;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User getUserById(String userId){
        return userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
    }
}
