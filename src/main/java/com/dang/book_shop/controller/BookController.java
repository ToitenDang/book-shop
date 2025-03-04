package com.dang.book_shop.controller;

import com.dang.book_shop.dto.request.BookCreationRequest;
import com.dang.book_shop.dto.request.BookUpdateRequest;
import com.dang.book_shop.dto.response.ApiResponse;
import com.dang.book_shop.entity.Book;
import com.dang.book_shop.service.BookService;
import com.dang.book_shop.service.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class BookController {

    private static final Logger log = LoggerFactory.getLogger(BookController.class);


    @Autowired
    private BookService bookService;
    @Autowired
    private CartService cartService;

    @PostMapping("/book")
    public ApiResponse<Book> addBook(@ModelAttribute @Valid BookCreationRequest request,
                                     @RequestParam("file") MultipartFile file) throws IOException {
        log.info("request: {}", request);

//        ApiResponse<Book> apiResponse = new ApiResponse<>();
//        apiResponse.setResult(bookService.addBook(request));
        return ApiResponse.<Book>builder()
                .result(bookService.addBook(request, file))
                .build();
    }

    @PutMapping("/book/{bookId}")
    public ApiResponse<Book> updateBook( @PathVariable String bookId, @RequestBody BookUpdateRequest request){
//        ApiResponse<Book> apiResponse = new ApiResponse<>();
//        apiResponse.setMessage("Cập nhật thành công!");
//        apiResponse.setResult(bookService.updateBook(request, bookId));
        return ApiResponse.<Book>builder()
                .message("Cập nhật thành công!")
                .result(bookService.updateBook(request, bookId))
                .build();
    }


    @GetMapping("/books")
    public ApiResponse<List<Book>> getBooks(){

//        ApiResponse<List<Book>> apiResponse = new ApiResponse<>();
//        apiResponse.setResult(bookService.getBooks());
        return ApiResponse.<List<Book>>builder()
                .result(bookService.getBooks())
                .build();
    }

    @GetMapping("/books/{cateId}")
    public List<Book> getBookByCategory(@PathVariable String cateId){
        return bookService.getBookByCategory(cateId);
    }

    @GetMapping("/book/{bookId}")
    public Book getBookById(@PathVariable String bookId){
        return bookService.getBookById(bookId);
    }

    @DeleteMapping("/book/{bookId}")
    public void deleteBook(@PathVariable String bookId){
        bookService.deleteBook(bookId);
    }

    @GetMapping("/books/search")
    public List<Book> searchBooks(@RequestParam String keyword){
        return bookService.searchBooks(keyword);
    }

    @PostMapping("/books/book/add-to-cart")
    public void addBookToCart(@RequestParam String userId,
                              @RequestParam String bookId,
                              @RequestParam int quantity){
        cartService.addProductToCart(userId, bookId, quantity);
    }

}
