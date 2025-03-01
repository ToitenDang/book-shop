package com.dang.book_shop.dto.request;

import com.dang.book_shop.validator.DobConstraint;
import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {
    private String password;

    private String fullName;

    @DobConstraint(min = 16, message = "DOB_INVALID")
    private LocalDate dob;

    private String address;

    private String phone;
}
