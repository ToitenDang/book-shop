package com.dang.book_shop.dto.request;

import com.dang.book_shop.entity.Category;
import jakarta.validation.constraints.Min;
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
public class BookCreationRequest {
    @Size(min = 3, message = "TITLE_INVALID")
    private String title;

    private String author;

    private String publisher;

    private LocalDate publicationDate;

    @Min(value = 1000, message = "PRICE_INVALID")
    private int price;

    private String language;

    private String genre;

    private String description;

    private String category;
}
