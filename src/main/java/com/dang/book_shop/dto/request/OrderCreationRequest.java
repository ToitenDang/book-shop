package com.dang.book_shop.dto.request;

import com.dang.book_shop.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreationRequest {

    private String userId;

    private String address;

    private String phone;

    private String note;

    private int fee;

}
