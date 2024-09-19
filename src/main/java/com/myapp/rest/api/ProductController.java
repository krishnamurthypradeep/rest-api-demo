package com.myapp.rest.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
		
		if(map.containsKey(product.productId())) {
			throw new ProductAlreadyExistsException("Product Already Exists");
		}
		return map.putIfAbsent(product.productId(), product);
	}
	
	//http://IP:8080/api/v1/products/Iphone16
	@GetMapping("/{name}")
	public List<Product> listProductsByName(@PathVariable("name") String productName){
		List<Product> products = map.values().stream().filter(p -> p.productName().equalsIgnoreCase(productName))
				.collect(Collectors.toList());
		if(products.size() ==0) {
			throw new ProductNotFoundException("Product With Name "+productName+" Does not exist");
		}
		return products;
	}
	
	//http://IP:8080/api/v1/products/1
		@DeleteMapping("/{id}")
		public String deleteProduct(@PathVariable("id") Integer productId){
			if(!map.containsKey(productId)) {
				throw new ProductNotFoundException("Product With Name "+productId+" Does not exist");
			}
			
			Product product = map.remove(productId);
			return product.productName() + " deleted";
		}
		

		//http://IP:8080/api/v1/products 
		// httpbody {"productName":"","price":78787,"rating":}
		@PutMapping("/{id}")
		public Product updateProduct(@PathVariable("id") Integer productId, @RequestBody Product product){
			
			return map.computeIfPresent(productId, (Integer key,Product oldProduct) -> product);
		}	
		
		
		
		
	
	// @PathVariable
	// @RequestBody
	// @RequestHeader
	// @RequestParam	
	
		// GET
		// GET with an identifier
		// POST creates a new resource
		// PUT updates a resource
		// DELETE delete a resource
	
	

}
