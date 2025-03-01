package com.dang.book_shop.repository;

import com.dang.book_shop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

    @Query("SELECT o from Order o  WHERE o.user.id = :userId")
    List<Order> findAllOrderByUserId(String userId);
}
