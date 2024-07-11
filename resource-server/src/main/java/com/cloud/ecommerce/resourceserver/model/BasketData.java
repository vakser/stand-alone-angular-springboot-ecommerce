package com.cloud.ecommerce.resourceserver.model;

import java.util.List;

import jakarta.annotation.Nonnull;
import lombok.Data;

@Data
public class BasketData {
	  @Nonnull
	  private String Id;
	  private List<BasketItem> Items;

	  public BasketData() {}
}
