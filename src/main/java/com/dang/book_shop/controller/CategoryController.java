package com.dang.book_shop.controller;

import com.dang.book_shop.dto.request.CategoryCreationRequest;
import com.dang.book_shop.dto.response.ApiResponse;
import com.dang.book_shop.entity.Category;
import com.dang.book_shop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/category")
    public Category createCategory(@RequestBody CategoryCreationRequest request) {
        return categoryService.createCategory(request);
    }

    @GetMapping("/categories")
    public List<Category> getCategories() {
        return categoryService.getCategories();
    }

    @GetMapping("/category/{cateId}")
    public Category getCategoryById(@PathVariable String cateId) {
        return categoryService.getCategoryById(cateId);
    }

    @DeleteMapping("/category/{cateId}")
    public void deleteCategory(@PathVariable String cateId) {
        categoryService.deleteCategory(cateId);
    }

    @PutMapping("/categories/{cateId}")
    public ApiResponse<Category> updateCategory(@PathVariable String cateId, @RequestBody CategoryCreationRequest category){
        ApiResponse<Category> apiResponse = new ApiResponse<>();
        apiResponse.setResult(categoryService.udpateCategory(category, cateId));
        return apiResponse;
    }
}
