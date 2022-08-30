package com.deekol.buysell.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deekol.buysell.domain.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

}
