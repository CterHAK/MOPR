package com.example.baitapAPI;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BaitapApiApplication {

    @Autowired
    private DataSource dataSource; // Kiểm tra nếu có lỗi inject tại đây

    public static void main(String[] args) {
        SpringApplication.run(BaitapApiApplication.class, args);
    }
}

