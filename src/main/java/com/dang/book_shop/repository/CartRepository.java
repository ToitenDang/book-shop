package com.dang.book_shop.repository;

import com.dang.book_shop.entity.Cart;
import com.dang.book_shop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, String> {
    @Query("SELECT c FROM Cart c WHERE c.user.id = :userId")
    Cart findByUserId(String userId);

}
