package com.keshavbansal.ECommerceStore.service.product;

import com.keshavbansal.ECommerceStore.dto.ProductDto;
import com.keshavbansal.ECommerceStore.model.Product;
import com.keshavbansal.ECommerceStore.request.AddProductRequest;
import com.keshavbansal.ECommerceStore.request.UpdateProductRequest;

import java.util.List;

public interface IProductService {

    Product getProductById(Long productId);

    List<Product> getAllProducts();

    List<Product> getProductsByCategory(String category);

    List<Product> getProductsByBrand(String brand);

    List<Product> getProductsByName(String name);

    List<Product> getProductsByBrandAndName(String brand, String name);

    List<Product> getProductsByCategoryAndBrand(String category, String brand);

    Product addProduct(AddProductRequest request);

    Product updateProduct(UpdateProductRequest request, Long productId);

    Product deleteProduct(Long productId);

    Long countProducts();

    Long countProductsByCategory(String category);

    Long countProductsByBrand(String brand);

    Long countProductsByName(String name);

    Long countProductsByBrandAndName(String brand, String name);

    ProductDto convertToDto(Product product);

    List<ProductDto> getConvertedProducts(List<Product> products);
}
