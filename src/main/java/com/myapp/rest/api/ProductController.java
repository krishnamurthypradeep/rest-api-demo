package com.myapp.rest.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.rest.model.Product;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
	
	@GetMapping
	public List<Product> listProducts(){
		return List.of(new Product(1,"Iphone16",85456.5,4.7),
				new Product(3,"SasmungFlip",95456.5,4.8));
	}

}
