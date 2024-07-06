package com.cloud.ecommerce.resourceserver.model;

import java.util.Set;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="category")
public class Category {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="categoryId")
	private Long categoryId;
	
	
	@Column(name="categoryName")
	private String categoryName;

	public Category() {}
	
	public Category(String categoryName) {
		super();
		this.categoryName= categoryName;
	}
	
	public Category(Long categoryId) {
		super();
		this.categoryId= categoryId;
	}
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="category")
	private Set<Product> products;
}
