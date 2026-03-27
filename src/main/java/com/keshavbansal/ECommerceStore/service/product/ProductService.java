package com.keshavbansal.ECommerceStore.service.product;

import com.keshavbansal.ECommerceStore.globalException.ProductNotFoundException;
import com.keshavbansal.ECommerceStore.model.Category;
import com.keshavbansal.ECommerceStore.model.Product;
import com.keshavbansal.ECommerceStore.repository.CategoryRepository;
import com.keshavbansal.ECommerceStore.repository.ProductRepository;
import com.keshavbansal.ECommerceStore.request.AddProductRequest;
import com.keshavbansal.ECommerceStore.request.UpdateProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {


    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    @Override
    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(()->new ProductNotFoundException("Product not found with id: " + productId));
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();
        if(products.isEmpty()){
            throw new ProductNotFoundException("Product not found in the database");
        }
        return products;
    }

    @Override
    public List<Product> getProductsByCategory(String  category) {
        List<Product> products = productRepository.findByCategoryName(category);
        if(products.isEmpty()){
            throw new ProductNotFoundException("Product not found with category: " + category);
        }
        return products;
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        List<Product> products = productRepository.findByBrand(brand);
        if(products.isEmpty()){
            throw new ProductNotFoundException("Product not found with brand: " + brand);
        }
        return products;
    }

    @Override
    public List<Product> getProductsByName(String name) {
        List<Product> products = productRepository.findByName(name);
        if(products.isEmpty()){
            throw new ProductNotFoundException("Product not found with name: " + name);
        }
        return products;
    }

    @Override
    public List<Product> getProductsByBrandAndName(String brand, String name) {
        List<Product> products = productRepository.findByBrandAndName(brand, name);
        if(products.isEmpty()){
            throw new ProductNotFoundException("Product not found with name: " + name + " and brand: " + brand);
        }
        return products;
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
        List<Product> products = productRepository.findByBrandAndCategoryName(brand,category);
        if(products.isEmpty()){
            throw new ProductNotFoundException("Product not found with category: " + category + " and brand: " + brand);
        }
        return products;
    }

    @Override
    public Product addProduct(AddProductRequest request) {

        // FIRST WE CHECK IF CATEGORY EXIST OR NOT, IF EXIST WE WILL ADD DIRECTLY IN CATEGORY ELSE CREATE IT AND THEN ADD IT

        Category category = Optional.ofNullable(categoryRepository.findByName(request.getCategory().getName()))
                .orElseGet(()-> {
                    Category newCategory = new Category(request.getCategory().getName());
                        return categoryRepository.save(newCategory);
                });


        request.setCategory(category);
        Product product = createProduct(request, category);
        productRepository.save(product);

        return product;

    }

    private Product createProduct(AddProductRequest request, Category category) {
        return new Product(
                request.getName(),
                request.getBrand(),
                request.getDescription(),
                request.getPrice(),
                request.getQuantity(),
                category
        );
    }

    @Override
    public Product updateProduct(UpdateProductRequest request, Long productId) {

        return productRepository.findById(productId)
                .map(existingProduct -> updateExistingProduct(existingProduct, request))
                .map(productRepository::save)
                .orElseThrow(()->new ProductNotFoundException("Product not found with id: " + productId));

    }

    private Product updateExistingProduct(Product existingProduct, UpdateProductRequest request) {
        existingProduct.setName(request.getName());
        existingProduct.setBrand(request.getBrand());
        existingProduct.setDescription(request.getDescription());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setQuantity(request.getQuantity());
        //Category  category = categoryRepository.findByName(request.getCategory().getName());
        Category category = Optional.ofNullable(categoryRepository.findByName(request.getCategory().getName()))
                .orElseGet(() -> categoryRepository.save(new Category(request.getCategory().getName())));

        existingProduct.setCategory(category);
        return existingProduct;
    }

    @Override
    public Product deleteProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(()->new ProductNotFoundException("Product not found with id: " + productId));
        productRepository.delete(product);
        return product;
    }

    @Override
    public Long countProducts() {
        return productRepository.count();
    }

    @Override
    public Long countProductsByCategory(String category) {
        return productRepository.countByCategoryName(category);
    }

    @Override
    public Long countProductsByBrand(String brand) {
        return productRepository.countByBrand(brand);
    }

    @Override
    public Long countProductsByName(String name) {
        return productRepository.countByName(name);
    }

    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand,name);
    }
}
