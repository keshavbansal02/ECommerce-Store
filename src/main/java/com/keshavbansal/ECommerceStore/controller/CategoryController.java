package com.keshavbansal.ECommerceStore.controller;

import com.keshavbansal.ECommerceStore.globalException.AlreadyExistsException;
import com.keshavbansal.ECommerceStore.globalException.ResourceNotFoundException;
import com.keshavbansal.ECommerceStore.model.Category;
import com.keshavbansal.ECommerceStore.response.ApiResponse;
import com.keshavbansal.ECommerceStore.service.category.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;


@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final ICategoryService categoryService;

    @GetMapping("/get-all")
    public ResponseEntity<ApiResponse> getAllCategories() {
        try {
            List<Category> categories = categoryService.getAllCategories();
            return ResponseEntity.ok(new ApiResponse("Categories retrieved successfully", categories));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Failed to retrieve categories", INTERNAL_SERVER_ERROR));
        }
    }

    @PostMapping("/add-category")
    public ResponseEntity<ApiResponse> addCategory(@RequestBody Category category) {
        try{
            Category savedCategory = categoryService.addCategory(category);
            return ResponseEntity.ok(new ApiResponse("Category added successfully",savedCategory));

        }catch (AlreadyExistsException e){

            return  ResponseEntity.status(CONFLICT).body(new ApiResponse(e.getMessage(), null));

        }
    }

    @GetMapping("/get-by-id/")
    public ResponseEntity<ApiResponse> getCategoryById(@RequestParam Long id) {
        try {
            Category category = categoryService.getCategoryById(id);
            return ResponseEntity.ok(new ApiResponse("Category retrieved successfully", category));
        } catch (ResourceNotFoundException e) {
            return  ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/get-by-name/")
    public ResponseEntity<ApiResponse> getCategoryByName(@RequestParam String name) {
        try {
            Category category = categoryService.getCategoryByName(name);
            return ResponseEntity.ok(new ApiResponse("Category retrieved successfully", category));
        } catch (ResourceNotFoundException e) {
            return  ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }


    @DeleteMapping("/delete-by-id/")
    public ResponseEntity<ApiResponse> deleteCategory(@RequestParam Long id) {
        try {
            categoryService.deleteCategory(id);
            return ResponseEntity.ok(new ApiResponse("Category deleted successfully", null));
        } catch (ResourceNotFoundException e) {
            return  ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/update-by-id/")
    public ResponseEntity<ApiResponse> updateCategory(@RequestParam Long id, @RequestBody Category category) {
        try {
            Category updatedCategory = categoryService.updateCategory(category,id);
            return ResponseEntity.ok(new ApiResponse("Category deleted successfully", updatedCategory));
        } catch (ResourceNotFoundException e) {
            return  ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
