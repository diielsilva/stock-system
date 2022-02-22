package com.example.invetory_system.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.invetory_system.helpers.ResponseValue;
import com.example.invetory_system.models.Product;
import com.example.invetory_system.services.ProductService;

@RestController
@RequestMapping("products")
public class ProductController {
	private ProductService service;

	public ProductController(ProductService service) {
		super();
		this.service = service;
	};

	@PostMapping
	public ResponseEntity<ResponseValue> save(@RequestBody Product product) {
		return this.service.save(product);
	}

	@GetMapping
	public ResponseEntity<ResponseValue> findAll() {
		return this.service.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseValue> findById(@PathVariable Long id) {
		return this.service.findById(id);
	}

	@GetMapping("/search")
	public ResponseEntity<ResponseValue> findByName(@RequestParam String name) {
		return this.service.findByName(name);
	}

	@PutMapping
	public ResponseEntity<ResponseValue> update(@RequestBody Product product) {
		return this.service.update(product);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseValue> delete(@PathVariable Long id) {
		return this.service.delete(id);
	}

}
