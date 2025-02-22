package com.dang.book_shop.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

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
}
