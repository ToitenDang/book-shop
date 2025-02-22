package com.dang.book_shop.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreationRequest {

    private String email;

    private String password;

    private String fullName;

    private LocalDate dob;

    private String address;

    private String phone;
}
