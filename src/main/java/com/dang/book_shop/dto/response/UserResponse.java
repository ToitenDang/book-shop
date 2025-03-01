package com.dang.book_shop.dto.response;

import com.dang.book_shop.entity.Order;
import com.dang.book_shop.enums.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserResponse {
    private String id;
    private String email;

    private String fullName;

    private LocalDate dob;

    private String address;

    private String phone;
    private Set<Order> orders;

    private Role role;
}
