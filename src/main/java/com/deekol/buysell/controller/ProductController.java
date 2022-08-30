package com.deekol.buysell.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deekol.buysell.domain.ProductEntity;
import com.deekol.buysell.payload.response.MessageResponse;
import com.deekol.buysell.repository.ProductRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
@CrossOrigin
public class ProductController {
	
	private final ProductRepository productRepository;

	@GetMapping
	public List<ProductEntity> getAll() {		
		return productRepository.findAll();
	}
	
	@GetMapping("{id}")
	public ProductEntity getOne(@PathVariable("id") ProductEntity productEntity) {
		return productEntity;
	}
	
	@PostMapping
	public ProductEntity create(@RequestBody ProductEntity productEntity) {
		return productRepository.save(productEntity);
	}
	
	@PutMapping("{id}")
	public ProductEntity update(@PathVariable("id") ProductEntity productFromDb, @RequestBody ProductEntity productFromForm) {
		BeanUtils.copyProperties(productFromForm, productFromDb, "id");
		return productRepository.save(productFromDb);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable("id") ProductEntity productEntity) {
		productRepository.delete(productEntity);
		return ResponseEntity.ok(new MessageResponse("Product deleted successfully!"));
	}
}
