package com.dang.book_shop.exception;

public enum ErrorCode {

    UNCATEGORIZED(9999, "Uncategorized error!"),

    BOOK_NOT_FOUND(1001, "Book not found!"),
    CATEGORY_NOT_FOUND(1001, "Category not found!"),
    CART_NOT_FOUND(1001, "Cart not found!"),
    USER_NOT_FOUND(1001, "User not found!"),
    INVALID_KEY(1001, "Invalid message key!"),


    BOOK_EXISTED(1002, "Book existed!"),
    TITLE_INVALID(1003, "The title must be at least 3 characters!"),
    PRICE_INVALID(1003, "The price must be higher than 1000!")

    ;

    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }


    public String getMessage() {
        return message;
    }

}
