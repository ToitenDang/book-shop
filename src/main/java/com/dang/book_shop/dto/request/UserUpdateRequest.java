package com.dang.book_shop.dto.request;

import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {
    private String password;

    private String fullName;

    private LocalDate dob;

    private String address;

    private String phone;
}
