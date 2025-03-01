package com.dang.book_shop.service;

import com.dang.book_shop.dto.request.UserCreationRequest;
import com.dang.book_shop.dto.request.UserUpdateRequest;
import com.dang.book_shop.dto.response.UserResponse;
import com.dang.book_shop.entity.User;
import com.dang.book_shop.enums.Role;
import com.dang.book_shop.exception.AppException;
import com.dang.book_shop.exception.ErrorCode;
import com.dang.book_shop.mapper.UserMapper;
import com.dang.book_shop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserResponse createUser(UserCreationRequest request) {
        // Kiểm tra xem email đã tạo tài khoản hay chưa
        if(userRepository.existsByEmail(request.getEmail()))
            throw new AppException(ErrorCode.USER_EXISTED);
        // Tạo 1 đối tượng user bằng cách map thông tin từ request vào user
        User user = userMapper.toUser(request);

        // Mã hóa password của user trước khi lưu vào dbms
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setRole(Role.USER);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    public UserResponse updateUser(String userId, UserUpdateRequest request){
        // Lấy ra user cần cập nhật.
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        userMapper.updateUser(user, request);// Map thông tin mới từ request và user.
        System.out.println("User after update: "+ user.getAddress() + "request: " + request.getPassword());
        return userMapper.toUserResponse(userRepository.save(user));// lưu lại.
    }

    @PreAuthorize("hasRole('ADMIN')") // Kiểm tra trước lúc gọi hàm phải có cái role là ADMIN
    public List<UserResponse> getUsers(){
        log.info("In method getUsers");
        return userRepository.findAll().stream().map(userMapper::toUserResponse).toList();
    }

    @PostAuthorize("returnObject.email == authentication.name") // Sau khi thực hiện xong phương thức mới kiểm tra hợp lệ thì trả về kết quả
    public UserResponse getUserById(String userId){
        log.info("In method getUserById");
        return userMapper.toUserResponse(
                userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND)));
    }

    public UserResponse getMyInfo(){
        var context = SecurityContextHolder.getContext(); // Trong context lưu trữ thông tin xác thực
        String email = context.getAuthentication().getName(); // Lấy ra email lưu trong authentication sau khi xác thực

        // Lấy user theo email xác thực của người dùng trong dbms
        User user = userRepository.findByEmail(email).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        return userMapper.toUserResponse(user);
    }

    public void deleteUser(String userId){
        userRepository.deleteById(userId);
    }
}
