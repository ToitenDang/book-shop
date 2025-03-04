package com.dang.book_shop.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookUpdateRequest {
    @Size(min = 3, message = "TITLE_INVALID")
    private String title;

    private String author;

    private String publisher;

    private LocalDate publicationDate;

    @Min(value = 1000, message = "PRICE_INVALID")
    private int price;

    private int quantity;

    private int sold;

    private String language;

    private String genre;

    private String description;
}
