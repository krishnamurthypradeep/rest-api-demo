package com.myapp.rest.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.rest.model.Product;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
	
	private Map<Integer,Product> map;
	
	@PostConstruct
	public void initialize() {
		map = new HashMap<>();
		map.put(1, new Product(1,"Iphone16",85456.5,4.7));
		map.put(3, new Product(3,"SasmungFlip",95456.5,4.8));
	}
	
	//http://IP:8080/api/v1/products
	@GetMapping
	public List<Product> listProducts(){
		return map.values().stream().collect(Collectors.toList());
	}
	
	//http://IP:8080/api/v1/products
	@PostMapping
	public Product createProduct(@RequestBody Product product){
		return map.putIfAbsent(product.productId(), product);
	}
	
	//http://IP:8080/api/v1/products/Iphone16
	@GetMapping("/{name}")
	public List<Product> listProductsByName(@PathVariable("name") String productName){
		return map.values().stream().filter(p -> p.productName().equalsIgnoreCase(productName))
				.collect(Collectors.toList());
	}
	
	//http://IP:8080/api/v1/products/Iphone16
		@DeleteMapping("/{id}")
		public String deleteProduct(@PathVariable("id") Integer productId){
			
			Product product = map.remove(productId);
			return product.productName() + " deleted";
		}
		
		
	
	
	
	
	

}
