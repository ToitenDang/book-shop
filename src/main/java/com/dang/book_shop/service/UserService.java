package com.dang.book_shop.service;

import com.dang.book_shop.dto.request.UserCreationRequest;
import com.dang.book_shop.dto.request.UserUpdateRequest;
import com.dang.book_shop.dto.response.UserResponse;
import com.dang.book_shop.entity.User;
import com.dang.book_shop.exception.AppException;
import com.dang.book_shop.exception.ErrorCode;
import com.dang.book_shop.mapper.UserMapper;
import com.dang.book_shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public UserResponse createUser(UserCreationRequest request) {
        // Kiểm tra xem email đã tạo tài khoản hay chưa
        if(userRepository.existsByEmail(request.getEmail()))
            throw new AppException(ErrorCode.USER_EXISTED);
        // Tạo 1 đối tượng user bằng cách map thông tin từ request vào user
        User user = userMapper.toUser(request);
        // Lưu user mới vào database
        //userRepository.save(user);

//        UserResponse userResponse = new UserResponse();
//
//        userResponse.setId(user.getId());
//        userResponse.setEmail(user.getEmail());
//        userResponse.setFullName(user.getFullName());
//        userResponse.setDob(user.getDob());
//        userResponse.setAddress(user.getAddress());
//        userResponse.setPhone(user.getPhone());
        return userMapper.toUserResponse(userRepository.save(user));
    }

    public UserResponse updateUser(String userId, UserUpdateRequest request){
        // Lấy ra user cần cập nhật.
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        userMapper.updateUser(user, request);// Map thông tin mới từ request và user.
        System.out.println("User after update: "+ user.getAddress() + "request: " + request.getPassword());
        return userMapper.toUserResponse(userRepository.save(user));// lưu lại.
    }

    public List<UserResponse> getUsers(){
        return userRepository.findAll().stream().map(item -> {
            return userMapper.toUserResponse(item);
        }).toList();
    }

    public UserResponse getUserById(String userId){
        return userMapper.toUserResponse(
                userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND)));
    }

    public void deleteUser(String userId){
        userRepository.deleteById(userId);
    }
}
