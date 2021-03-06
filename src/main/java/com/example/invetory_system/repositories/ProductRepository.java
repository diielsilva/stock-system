package com.example.invetory_system.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.invetory_system.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	List<Product> findByNameContaining(String name);
}
