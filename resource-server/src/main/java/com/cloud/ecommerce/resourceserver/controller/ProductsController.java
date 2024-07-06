package com.cloud.ecommerce.resourceserver.controller;

import com.cloud.ecommerce.resourceserver.dto.ProductResponseDto;
import com.cloud.ecommerce.resourceserver.dto.ProductResponseListDto;
import com.cloud.ecommerce.resourceserver.model.Brand;
import com.cloud.ecommerce.resourceserver.model.Category;
import com.cloud.ecommerce.resourceserver.repository.BrandRepository;
import com.cloud.ecommerce.resourceserver.repository.CategoryRepository;
import com.cloud.ecommerce.resourceserver.service.IProductService;
import com.cloud.ecommerce.resourceserver.specification.ProductSpecParams;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/shop")
public class ProductsController {

    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final IProductService productService;

    public ProductsController(CategoryRepository categoryRepository, BrandRepository brandRepository, IProductService productService) {
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
        this.productService = productService;
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getCategories() {
        List<Category> categories = categoryRepository.findAll();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/brands")
    public ResponseEntity<List<Brand>> getBrands() {
        List<Brand> brands = brandRepository.findAll();
        return ResponseEntity.ok(brands);
    }

    @GetMapping("/products")
    public ResponseEntity<ProductResponseListDto> getProducts(ProductSpecParams requestParams) {
        ProductResponseListDto productList = productService.getProductList(requestParams);
        return ResponseEntity.ok(productList);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable long id) {
        ProductResponseDto product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }
}
