package com.dang.book_shop.service;

import com.dang.book_shop.dto.request.CategoryCreationRequest;
import com.dang.book_shop.entity.Category;
import com.dang.book_shop.exception.AppException;
import com.dang.book_shop.exception.ErrorCode;
import com.dang.book_shop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category createCategory(CategoryCreationRequest request){
        Category category = new Category();
        category.setName(request.getName());
        return  categoryRepository.save(category);
    }

    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }

    public Category getCategoryById(String id){
        return categoryRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
    }

    public void deleteCategory(String id){
        categoryRepository.deleteById(id);
    }

    public Category udpateCategory(CategoryCreationRequest request, String cateId){
        Category category = getCategoryById(cateId);
        category.setName(request.getName());
        return categoryRepository.save(category);
    }
}
