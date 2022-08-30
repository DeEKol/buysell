package com.deekol.buysell.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deekol.buysell.domain.ProductTypeEntity;

public interface ProductTypeRepository extends JpaRepository<ProductTypeEntity, Long> {

}
