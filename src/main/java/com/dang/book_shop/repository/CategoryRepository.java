package com.dang.book_shop.repository;

import com.dang.book_shop.entity.Category;
import com.dang.book_shop.service.CategoryService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

    Category getCategoryByName(String name);
}
