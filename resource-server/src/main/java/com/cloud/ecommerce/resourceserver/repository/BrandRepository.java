package com.cloud.ecommerce.resourceserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloud.ecommerce.resourceserver.model.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {

}
