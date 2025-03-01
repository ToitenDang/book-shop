package com.dang.book_shop.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {

    UNCATEGORIZED(9999, "Uncategorized error!", HttpStatus.INTERNAL_SERVER_ERROR),
    UNAUTHENTICATED(1004, "Unauthenticated!", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1007, "You don't have permission", HttpStatus.FORBIDDEN),

    BOOK_NOT_FOUND(1001, "Book not found!", HttpStatus.NOT_FOUND),
    CATEGORY_NOT_FOUND(1001, "Category not found!", HttpStatus.NOT_FOUND),
    CART_NOT_FOUND(1001, "Cart not found!", HttpStatus.NOT_FOUND),
    USER_NOT_FOUND(1001, "User not found!", HttpStatus.NOT_FOUND),
    ORDER_NOT_FOUND(1001, "Order not found!", HttpStatus.NOT_FOUND),

    BOOK_EXISTED(1002, "Book existed!", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1002, "User existed!", HttpStatus.BAD_REQUEST),

    INVALID_KEY(1001, "Invalid message key!", HttpStatus.BAD_REQUEST),
    TITLE_INVALID(1003, "The title must be at least {min} characters!", HttpStatus.BAD_REQUEST),
    PRICE_INVALID(1003, "The price must be higher than {min}!", HttpStatus.BAD_REQUEST),
    DOB_INVALID(1003, "Your age must be at least {min}", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1003, "Your password must be at least {min}", HttpStatus.BAD_REQUEST),
    EMAIL_INVALID(1003, "Invalid email format", HttpStatus.BAD_REQUEST)
    ;

    private int code;
    private String message;
    private HttpStatusCode statusCode;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

}
