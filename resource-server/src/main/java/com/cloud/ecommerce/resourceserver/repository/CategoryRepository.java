package com.cloud.ecommerce.resourceserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloud.ecommerce.resourceserver.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
