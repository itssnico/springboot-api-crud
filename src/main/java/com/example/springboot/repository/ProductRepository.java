package com.example.springboot.repository;

import com.example.springboot.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository //To indicate to Spring that this interface is a Bean/Repository
public interface ProductRepository extends JpaRepository <ProductModel, UUID>{
}
