package com.dang.book_shop.service;

import com.dang.book_shop.dto.request.BookCreationRequest;
import com.dang.book_shop.dto.request.BookUpdateRequest;
import com.dang.book_shop.entity.Book;
import com.dang.book_shop.entity.Category;
import com.dang.book_shop.exception.AppException;
import com.dang.book_shop.exception.ErrorCode;
import com.dang.book_shop.repository.BookRepository;
import com.dang.book_shop.repository.CategoryRepository;
import com.dang.book_shop.service.upload.UploadImageFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    protected CategoryRepository categoryRepository;

    @Autowired
    private UploadImageFile uploadImageFile;

    public Book addBook(BookCreationRequest request, MultipartFile file) throws IOException {
        Category category = Optional.ofNullable(categoryRepository.getCategoryByName(request.getCategory()))
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));

        if (bookRepository.existsByTitle(request.getTitle())) {
            throw new AppException(ErrorCode.BOOK_EXISTED);
        }

        //request.setCategory(category);
        Book book = buildBook(request, category);
        book.setImage(uploadImageFile.uploadImage(file));

        return bookRepository.save(book);
    }

    private Book buildBook(BookCreationRequest request, Category category) {
        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setPublisher(request.getPublisher());
        book.setPublicationDate(request.getPublicationDate());
        book.setPrice(request.getPrice());
        book.setQuantity(request.getQuantity());
        book.setSold(request.getSold());
        book.setLanguage(request.getLanguage());
        book.setGenre(request.getGenre());
        book.setDescription(request.getDescription());
        book.setCategory(category);
        return book;
    }

    public Book updateBook(BookUpdateRequest request, String bookId){
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new AppException(ErrorCode.BOOK_NOT_FOUND));

        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setPublisher(request.getPublisher());
        book.setPublicationDate(request.getPublicationDate());
        book.setPrice(request.getPrice());
        book.setQuantity(request.getQuantity());
        book.setSold(request.getSold());
        book.setLanguage(request.getLanguage());
        book.setGenre(request.getGenre());
        book.setDescription(request.getDescription());

        bookRepository.save(book);
        return book;
    }


    public List<Book> getBooks(){
        return bookRepository.findAll();
    }

    public Book getBookById(String id){
        return bookRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.BOOK_NOT_FOUND));
    }

    public List<Book> getBookByCategory(String cateId){
        return bookRepository.findByCategoryId(cateId);
    }

    public void deleteBook(String id){
        bookRepository.deleteById(id);
    }

    public List<Book> searchBooks(String keyword){
        return bookRepository.searchBooks(keyword);
    }

}
