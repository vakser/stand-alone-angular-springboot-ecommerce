package com.cloud.ecommerce.resourceserver.model;

import java.util.Set;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="brand")
public class Brand {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="brandId")
	private Long brandId;
	
	
	@Column(name="brandName")
	private String brandName;
	public Brand() {}
	
	public Brand(String brandName) {
		super();
		this.brandName= brandName;
	}
	
	public Brand(Long brandId) {
		super();
		this.brandId= brandId;
	}
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="brand")
	private Set<Product> products;
	
	
	
	
}
