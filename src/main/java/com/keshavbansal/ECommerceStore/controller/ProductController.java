package com.keshavbansal.ECommerceStore.controller;

import com.keshavbansal.ECommerceStore.globalException.ResourceNotFoundException;
import com.keshavbansal.ECommerceStore.model.Product;
import com.keshavbansal.ECommerceStore.request.AddProductRequest;
import com.keshavbansal.ECommerceStore.request.UpdateProductRequest;
import com.keshavbansal.ECommerceStore.response.ApiResponse;
import com.keshavbansal.ECommerceStore.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/get-by-id/")
    public ResponseEntity<ApiResponse> getProductById(@RequestParam long id) {
        try {
            Product product = productService.getProductById(id);
            return ResponseEntity.ok(new ApiResponse("Product retrieved successfully", product));
        } catch (ResourceNotFoundException e) {
            return  ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<ApiResponse> getAllProducts() {
        try {
            List<Product> products = productService.getAllProducts();
            return ResponseEntity.ok(new ApiResponse("Product retrieved successfully", products));
        } catch (ResourceNotFoundException e) {
            return  ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/get-by-category/")
    public ResponseEntity<ApiResponse> getProductByCategory(@RequestParam String category) {
        try {
            List<Product> product = productService.getProductsByCategory(category);
            return ResponseEntity.ok(new ApiResponse("Product retrieved successfully of category: " + category , product));
        } catch (ResourceNotFoundException e) {
            return  ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/get-by-brand/")
    public ResponseEntity<ApiResponse> getProductByBrand(@RequestParam String brand) {
        try {
            List<Product> product = productService.getProductsByBrand(brand);
            return ResponseEntity.ok(new ApiResponse("Product retrieved successfully of brand: " + brand , product));
        } catch (ResourceNotFoundException e) {
            return  ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/get-by-name/")
    public ResponseEntity<ApiResponse> getProductByName(@RequestParam String name) {
        try {
            List<Product> product = productService.getProductsByName(name);
            return ResponseEntity.ok(new ApiResponse("Product retrieved successfully of name: " + name , product));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PostMapping("/add-product")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody AddProductRequest product) {
        try {
            Product savedProduct = productService.addProduct(product);
            return ResponseEntity.ok(new ApiResponse("Product added successfully", savedProduct));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Failed to retrieve categories", INTERNAL_SERVER_ERROR));
        }

    }

    @PutMapping("/update-product/")
    public ResponseEntity<ApiResponse> updateProductById(@RequestParam long id, @RequestBody UpdateProductRequest request) {
        try {
            Product updatedProduct = productService.updateProduct(request,id);
            return ResponseEntity.ok(new ApiResponse("Product updated successfully", updatedProduct));
        } catch (Exception e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/delete-product/")
    public ResponseEntity<ApiResponse> deleteProductById(@RequestParam long id) {
        try {
            Product deletedProduct = productService.deleteProduct(id);
            return ResponseEntity.ok(new ApiResponse("Product deleted successfully", deletedProduct));
        } catch (Exception e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/get-product-by-brand-and-name/")
    public ResponseEntity<ApiResponse> getProductsByBrandAndName(@RequestParam String brand, @RequestParam String name) {
        try {
            List<Product> products = productService.getProductsByBrandAndName(brand, name);
            return ResponseEntity.ok(new ApiResponse("Product retrieved successfully of brand: " + brand + " and name: " + name , products));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/get-product-by-category-and-name/")
    public ResponseEntity<ApiResponse> getProductsByBrandAndCategory(@RequestParam String brand, @RequestParam String category) {
        try {
            List<Product> products = productService.getProductsByCategoryAndBrand(brand, category);
            return ResponseEntity.ok(new ApiResponse("Product retrieved successfully of brand: " + brand + " and Category: " + category , products));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/count-all-product")
    public ResponseEntity<ApiResponse> countAllProduct() {
        try{
            long count = productService.countProducts();
            return ResponseEntity.ok(new ApiResponse("Total number of products in the database: ", count));
        }catch(Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/count-product-by-category/")
    public ResponseEntity<ApiResponse> countProductsByCategory(@RequestParam String category){
        try{
            long count = productService.countProductsByCategory(category);
            return ResponseEntity.ok(new ApiResponse("Total number of products in the database: ", count));
        }catch(Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/count-product-by-name/")
    public ResponseEntity<ApiResponse> countProductsByName(@RequestParam String name){
        try{
            long count = productService.countProductsByName(name);
            return ResponseEntity.ok(new ApiResponse("Total number of products in the database: ", count));
        }catch(Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/count-product-by-brand/")
    public ResponseEntity<ApiResponse> countProductsByBrand(@RequestParam String brand){
        try{
            long count = productService.countProductsByBrand(brand);
            return ResponseEntity.ok(new ApiResponse("Total number of products in the database: ", count));
        }catch(Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/count-product-by-brand-and-name/")
    public ResponseEntity<ApiResponse> countProductsByBrandAndName(@RequestParam String brand,@RequestParam String name){
        try{
            long count = productService.countProductsByBrandAndName(brand,name);
            return ResponseEntity.ok(new ApiResponse("Total number of products in the database: ", count));
        }catch(Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

}
