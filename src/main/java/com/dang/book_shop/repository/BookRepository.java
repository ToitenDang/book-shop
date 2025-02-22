package com.dang.book_shop.repository;

import com.dang.book_shop.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
    List<Book> findByCategoryId(String cateId);

    boolean existsByTitle(String title);

    @Query("SELECT b from Book b WHERE "+
            "LOWER(b.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR "+
            "LOWER(b.author) LIKE LOWER(CONCAT('%', :keyword, '%')) OR "+
            "LOWER(b.genre) LIKE LOWER(CONCAT('%', :keyword, '%'))"
    )
    List<Book> searchBooks(String keyword);
}
