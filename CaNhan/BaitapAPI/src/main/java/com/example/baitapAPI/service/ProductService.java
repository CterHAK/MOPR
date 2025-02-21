package com.example.baitapAPI.service;

import com.example.baitapAPI.model.Product;
import com.example.baitapAPI.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Lấy danh mục sản phẩm
    public List<String> getAllCategories() {
        return productRepository.findAllCategories();
    }

    // Lấy danh sách sản phẩm theo danh mục
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    // Lấy 10 sản phẩm bán chạy nhất
    public List<Product> getTop10BestSellingProducts() {
        return productRepository.findTop10ByOrderByQuantitySoldDesc();
    }

    // Lấy 10 sản phẩm được tạo trong 7 ngày qua
    public List<Product> getRecentProducts() {
        LocalDateTime sevenDaysAgo = LocalDateTime.now().minusDays(7);
        return productRepository.findRecentProducts(sevenDaysAgo);
    }
}
