package com.example.invetory_system.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.invetory_system.exceptions.InvalidInputException;
import com.example.invetory_system.exceptions.ModelNotFoundException;
import com.example.invetory_system.helpers.ResponseValue;
import com.example.invetory_system.models.Product;
import com.example.invetory_system.repositories.ProductRepository;

@Service
public class ProductService {
	private ProductRepository repository;

	public ProductService(ProductRepository respotiry) {
		super();
		this.repository = respotiry;
	}

	// CRUD METHODS BELOW
	// ---------------------------------------------------------------------------------------------------
	@Transactional
	public ResponseEntity<ResponseValue> save(Product product) {
		if (this.validateToSave(product)) {
			this.repository.save(product);
			return ResponseEntity.ok(new ResponseValue("Product saved successfully"));
		} else {
			throw new InvalidInputException();
		}
	}

	public ResponseEntity<ResponseValue> findAll() {
		return ResponseEntity.ok(this.generateResult(this.repository.findAll(), new ResponseValue("List of products")));
	}

	public ResponseEntity<ResponseValue> findById(Long id) {
		Optional<Product> product = this.repository.findById(id);
		if (product.isEmpty()) {
			throw new ModelNotFoundException();
		} else {
			return ResponseEntity
					.ok(this.generateResult(Arrays.asList(product.get()), new ResponseValue("List of products")));
		}
	}

	public ResponseEntity<ResponseValue> findByName(String name) {
		if (this.validateToSearch(name)) {
			List<Product> products = this.repository.findByNameContaining(name);
			if (products.isEmpty()) {
				throw new ModelNotFoundException();
			} else {
				return ResponseEntity.ok(this.generateResult(products, new ResponseValue("List of products")));
			}
		} else {
			throw new InvalidInputException();
		}
	}

	@Transactional
	public ResponseEntity<ResponseValue> update(Product product) {
		if (this.validateToUpdate(product)) {
			Optional<Product> databaseProduct = this.repository.findById(product.getId());
			if (databaseProduct.isEmpty()) {
				throw new ModelNotFoundException();
			} else {
				this.repository.save(product);
				return ResponseEntity.ok(new ResponseValue("Product updated successfully"));
			}
		} else {
			throw new InvalidInputException();
		}
	}

	@Transactional
	public ResponseEntity<ResponseValue> delete(Long id) {
		Optional<Product> product = this.repository.findById(id);
		if (product.isEmpty()) {
			throw new ModelNotFoundException();
		} else {
			this.repository.delete(product.get());
			return ResponseEntity.ok(new ResponseValue("Product removed successfully"));
		}

	}

	// GENERATE RESULT FOR RESPONSE VALUE
	// ---------------------------------------------------------------------------------------------------
	public ResponseValue generateResult(List<Product> result, ResponseValue responseValue) {
		responseValue.setResult(result);
		return responseValue;
	}

	// VALIDATORS METHODS BELOW
	// ---------------------------------------------------------------------------------------------------
	public Boolean validateToSave(Product product) {
		Boolean isValid = true;
		if (product.getId() != null) {
			isValid = false;
		} else if (product.getName() == null || product.getName().isEmpty()) {
			isValid = false;
		}
		return isValid;
	}

	public Boolean validateToUpdate(Product product) {
		Boolean isValid = true;
		if (product.getId() == null) {
			isValid = false;
		} else if (product.getName() == null || product.getName().isEmpty()) {
			isValid = false;
		}
		return isValid;
	}

	public Boolean validateToSearch(String name) {
		Boolean isValid = true;
		if (name == null || name.isEmpty()) {
			isValid = false;
		}
		return isValid;
	}

}
