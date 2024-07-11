package com.cloud.ecommerce.resourceserver.controller;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.ecommerce.resourceserver.model.Basket;
import com.cloud.ecommerce.resourceserver.model.BasketData;
import com.cloud.ecommerce.resourceserver.repository.BasketRepository;

@RestController
@RequestMapping("/basket")
public class BasketController {

	private final BasketRepository basketRepository;

    public BasketController(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    @PostMapping()
	public ResponseEntity<Basket> createBasket(@RequestBody BasketData basketData) {
		Basket basket = new Basket(basketData.getId());
		basket.setItems(basketData.getItems());
		basketRepository.save(basket);
		return ResponseEntity.ok(basket);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Basket> getBasket(@PathVariable String id) {
		Optional<Basket> basketOpt = basketRepository.findById(id);
        return basketOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteBasket(@PathVariable String id) {
		Optional<Basket> basketOpt = basketRepository.findById(id);
		if (basketOpt.isPresent()) {
			basketRepository.delete(basketOpt.get());
			return ResponseEntity.ok(id);
		}
		return ResponseEntity.notFound().build();
	}
	
	
	/*
	 * @GetMapping() public ResponseEntity<Object> getBaskets(){
	 * System.out.println("Entered the get all basket"); Object list =
	 * basketRepository.findAll();
	 * 
	 * 
	 * return ResponseEntity.ok(list); }
	 */
	 
	
	
	
}
