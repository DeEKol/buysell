package com.deekol.buysell.controller;

import java.util.ArrayList;
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

import com.deekol.buysell.domain.ProductTypeEntity;
import com.deekol.buysell.payload.request.ProductTypeRequest;
import com.deekol.buysell.payload.response.MessageResponse;
import com.deekol.buysell.payload.response.ProductTypeResponse;
import com.deekol.buysell.repository.ProductTypeRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/product_type")
@AllArgsConstructor
@CrossOrigin
public class ProductTypeController {
	private final ProductTypeRepository productTypeRepository;

	@GetMapping
	public List<ProductTypeResponse> getAll() {	
		List<ProductTypeEntity> productTypeEntityList = productTypeRepository.findAll();
		
		List<ProductTypeResponse> productTypeResponseList = new ArrayList<ProductTypeResponse>();
		
		for(ProductTypeEntity e : productTypeEntityList) {
		ProductTypeResponse productTypeResponse = ProductTypeResponse.builder()
				.id(e.getId())
				.name(e.getName())
				.description(e.getDescription())
				.build();
		productTypeResponseList.add(productTypeResponse);
		}
		
		return productTypeResponseList;
	}
	
	@GetMapping("{id}")
	public ProductTypeResponse getOne(@PathVariable("id") ProductTypeEntity productTypeEntity) {
		ProductTypeResponse productTypeResponse = ProductTypeResponse.builder()
				.id(productTypeEntity.getId())
				.name(productTypeEntity.getName())
				.description(productTypeEntity.getDescription())
				.build();
		
		return productTypeResponse;
	}
	
	@PostMapping
	public ProductTypeResponse create(@RequestBody ProductTypeRequest productTypeRequest) {
		ProductTypeEntity productTypeEntity = ProductTypeEntity.builder()
				.name(productTypeRequest.getName())
				.description(productTypeRequest.getDescription())
				.build();
		productTypeRepository.save(productTypeEntity);
		
		ProductTypeResponse productTypeResponse = ProductTypeResponse.builder()
				.id(productTypeEntity.getId())
				.name(productTypeEntity.getName())
				.description(productTypeEntity.getDescription())
				.build();
		
		return productTypeResponse;
	}
	
	@PutMapping("{id}")
	public ProductTypeResponse update(@PathVariable("id") ProductTypeEntity productTypeFromDb, @RequestBody ProductTypeRequest productTypeFromForm) {
		BeanUtils.copyProperties(productTypeFromForm, productTypeFromDb, "id");
		productTypeRepository.save(productTypeFromDb);
		
		ProductTypeResponse productTypeResponse = ProductTypeResponse.builder()
				.id(productTypeFromDb.getId())
				.name(productTypeFromDb.getName())
				.description(productTypeFromDb.getDescription())
				.build();
		
		return productTypeResponse;
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable("id") ProductTypeEntity productTypeEntity) {
		productTypeRepository.delete(productTypeEntity);
		return ResponseEntity.ok(new MessageResponse("ProductType deleted successfully!"));
	}
}
