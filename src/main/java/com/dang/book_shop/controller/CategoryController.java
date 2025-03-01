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
    public ApiResponse<Category> createCategory(@RequestBody CategoryCreationRequest request) {
        return ApiResponse.<Category>builder()
                .result(categoryService.createCategory(request))
                .build();
    }

    @GetMapping("/categories")
    public ApiResponse<List<Category>> getCategories() {
        return ApiResponse.<List<Category>>builder()
                .result(categoryService.getCategories())
                .build();
    }

    @GetMapping("/category/{cateId}")
    public ApiResponse<Category> getCategoryById(@PathVariable String cateId) {
        return ApiResponse.<Category>builder()
                .result(categoryService.getCategoryById(cateId))
                .build();
    }

    @DeleteMapping("/category/{cateId}")
    public void deleteCategory(@PathVariable String cateId) {
        categoryService.deleteCategory(cateId);
    }

    @PutMapping("/categories/{cateId}")
    public ApiResponse<Category> updateCategory(@PathVariable String cateId, @RequestBody CategoryCreationRequest category){
//        ApiResponse<Category> apiResponse = new ApiResponse<>();
//        apiResponse.setResult(categoryService.updateCategory(category, cateId));
        return ApiResponse.<Category>builder()
                .result(categoryService.updateCategory(category, cateId))
                .build();
    }
}
