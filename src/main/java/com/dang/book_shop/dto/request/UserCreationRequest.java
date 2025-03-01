package com.dang.book_shop.dto.request;

import com.dang.book_shop.validator.DobConstraint;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
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

    @Email(message = "EMAIL_INVALID", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    @Size(min = 8, message = "PASSWORD_INVALID")
    private String password;

    private String fullName;

    @DobConstraint(min = 16, message = "DOB_INVALID")
    private LocalDate dob;

    private String address;

    private String phone;
}
