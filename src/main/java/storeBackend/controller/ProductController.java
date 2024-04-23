package storeBackend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import storeBackend.entity.Product;
import storeBackend.services.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {
	
	@Autowired
	ProductService productService;

	@GetMapping("/getAll")
	public List<Product> getAllProducts() {
		return productService.getAll();
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<?> getProduct(@PathVariable("id") int id) {
		Product product = productService.getProduct(id);
		
		if(product != null) {
			return ResponseEntity.ok(product);
		}
		else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"Product not found\"}");
	}
	
	@PostMapping("/add")
	public Product addProduct(@RequestBody Product req) {
		return productService.addProduct(req);
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateProduct(@Param("field") String field, @RequestBody Map<String, String> req) {
		if (field.compareTo("title") == 0) {
			int status = productService.updateProductName(req);
			if (status == 0)
				return ResponseEntity.internalServerError().build();
			return ResponseEntity.ok("Product title Updated");

		} else if (field.compareTo("price") == 0) {

			int status = productService.updateProductPrice(req);
			if (status == 0)
				return ResponseEntity.internalServerError().build();
			return ResponseEntity.ok("Product price Updated");
		} else if (field.compareTo("availability") == 0) {

			int status = productService.updateProductAvailability(req);

			if (status == 0)
				return ResponseEntity.internalServerError().build();
			return ResponseEntity.ok("Product availability Updated");

		}
		return ResponseEntity.internalServerError().build();
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteProduct(@RequestBody Map<String, Integer> req) {
		return productService.deleteProduct(req);
	}
}
