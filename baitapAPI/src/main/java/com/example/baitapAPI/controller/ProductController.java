package com.example.baitapAPI.controller;

import com.example.baitapAPI.model.Product;
import com.example.baitapAPI.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // API: Lấy tất cả danh mục sản phẩm
    @GetMapping("/categories")
    public List<String> getAllCategories() {
        return productService.getAllCategories();
    }

    // API: Lấy tất cả sản phẩm theo danh mục
    @GetMapping("/category/{category}")
    public List<Product> getProductsByCategory(@PathVariable String category) {
        return productService.getProductsByCategory(category);
    }

    // API: Lấy 10 sản phẩm bán chạy nhất
    @GetMapping("/top-selling")
    public List<Product> getTop10BestSellingProducts() {
        return productService.getTop10BestSellingProducts();
    }

    // API: Lấy 10 sản phẩm được tạo trong 7 ngày qua
    @GetMapping("/recent")
    public List<Product> getRecentProducts() {
        return productService.getRecentProducts();
    }
}
