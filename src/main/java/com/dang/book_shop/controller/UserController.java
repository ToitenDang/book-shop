package com.dang.book_shop.controller;

import com.cloudinary.Api;
import com.dang.book_shop.dto.request.UserCreationRequest;
import com.dang.book_shop.dto.request.UserUpdateRequest;
import com.dang.book_shop.dto.request.VerifyUserRequest;
import com.dang.book_shop.dto.response.ApiResponse;
import com.dang.book_shop.dto.response.UserResponse;
import com.dang.book_shop.entity.User;
import com.dang.book_shop.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request){
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.createUser(request));

        return apiResponse;
    }

    @PostMapping("/user/verify")
    public ApiResponse<String> verifyUser(@RequestBody VerifyUserRequest request){
        boolean isVerified = userService.verifyUser(request.getEmail(), request.getOtp());
        return (isVerified)
                ? ApiResponse.<String>builder().code(1000).message("Tạo tài khoản thành công").build():
                 ApiResponse.<String>builder().code(1001).message("Mã OTP không đúng!").build();
    }

    @PutMapping("/user/{userId}")
    public ApiResponse<UserResponse> updateUser(@PathVariable String userId, @RequestBody UserUpdateRequest request){
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.updateUser(userId, request));
        return apiResponse;
    }

    @GetMapping("/user")
    public ApiResponse<List<UserResponse>> getUsers(){
        var auth = SecurityContextHolder.getContext().getAuthentication();
        log.info("Username: {}", auth.getName());
        auth.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));
        ApiResponse<List<UserResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.getUsers());
        return apiResponse;
    }

    @GetMapping("/user/{userId}")
    public ApiResponse<UserResponse> getUserById(@PathVariable String userId){
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.getUserById(userId));
        return apiResponse;
    }

    @GetMapping("/user/myInfo")
    public ApiResponse<UserResponse> getMyInfo(){
        return ApiResponse.<UserResponse>builder()
                .result(userService.getMyInfo())
                .build();
    }

    @DeleteMapping("/user/{userId}")
    public void deleteUser(@PathVariable String userId){
        userService.deleteUser(userId);
    }
}
