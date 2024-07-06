package com.cloud.ecommerce.resourceserver.dto;

import com.cloud.ecommerce.resourceserver.model.Product;

import lombok.Data;

@Data
public class ProductResponseDto {
	private long productId;
	private double unitPrice;
	private long brandId;
	private long categoryId;
	private String title;
	private String sku;
	private String description;
	private String categoryName;
	private String brandName;
	private String imageUrl;
	
	public void populateDto(Product product) {
		
		this.productId = product.getProductId();
		this.categoryId = product.getCategory().getCategoryId();
		this.brandId = product.getBrand().getBrandId();
		this.categoryName = product.getCategory().getCategoryName();
		this.brandName = product.getBrand().getBrandName();
		this.unitPrice = product.getUnitPrice();
		this.title = product.getTitle();
		this.imageUrl = product.getImageUrl();
		this.description = product.getDescription();
		this.sku= product.getSku();
		
	}
	
}
