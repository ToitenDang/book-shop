package com.dang.book_shop.controller;

import com.dang.book_shop.dto.request.UserCreationRequest;
import com.dang.book_shop.dto.response.ApiResponse;
import com.dang.book_shop.dto.response.UserResponse;
import com.dang.book_shop.entity.User;
import com.dang.book_shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ApiResponse<UserResponse> createUser(@RequestBody UserCreationRequest request){
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.createUser(request));

        return apiResponse;
    }

    @GetMapping("user/{userId}")
    public User getUserById(@PathVariable String userId){
        return userService.getUserById(userId);
    }
}
