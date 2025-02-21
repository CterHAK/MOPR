package com.example.baitapAPI.repository;

import com.example.baitapAPI.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Lấy danh sách tất cả danh mục sản phẩm (distinct)
    @Query("SELECT DISTINCT p.category FROM Product p")
    List<String> findAllCategories();

    // Lấy tất cả sản phẩm theo danh mục
    List<Product> findByCategory(String category);

    // Lấy 10 sản phẩm có số lượng bán nhiều nhất
    List<Product> findTop10ByOrderByQuantitySoldDesc();

    // Lấy 10 sản phẩm được tạo trong vòng 7 ngày qua
    @Query("SELECT p FROM Product p WHERE p.createdAt >= :sevenDaysAgo ORDER BY p.createdAt DESC")
    List<Product> findRecentProducts(LocalDateTime sevenDaysAgo);
}
