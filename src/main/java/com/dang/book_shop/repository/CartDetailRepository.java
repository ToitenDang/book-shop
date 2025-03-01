package com.dang.book_shop.repository;

import com.dang.book_shop.entity.Book;
import com.dang.book_shop.entity.Cart;
import com.dang.book_shop.entity.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, String> {
    boolean existsByCartAndBook(Cart cart, Book book);

    CartDetail findByCartAndBook(Cart cart, Book book);

    List<CartDetail> findAllByCart(Cart cart);

    void deleteAllByCartId(String cartId);
}
