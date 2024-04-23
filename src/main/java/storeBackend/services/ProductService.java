package storeBackend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import storeBackend.dao.ProductRepository;
import storeBackend.entity.Product;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	public List<Product> getAll() {
		List<Product> list=new ArrayList<Product>();
		Iterable<Product> iterable = productRepository.findAll();
		for(Product product : iterable) list.add(product);
		return list;
	}

	public Product getProduct(int id) {
		try {
			Product product = productRepository.findById(id).get();
			return product;
		} catch (Exception e) {
			return null;
		}
	}

	public Product addProduct(Product prod) {
		return productRepository.save(prod);
	}

	public int updateProductName(Map<String, String> req) {
		int id = Integer.parseInt(req.get("id"));
		String title = req.get("title");
		return productRepository.changeProductTitle(id, title);
	}

	public int updateProductPrice(Map<String, String> req) {
		int id = Integer.parseInt(req.get("id"));
		String price = req.get("price");
		if (price != null)
			return productRepository.changeProductPrice(id, price);
		return 0;
	}

	public int updateProductAvailability(Map<String, String> req) {
		int id = Integer.parseInt(req.get("id"));
		int availability = Integer.parseInt(req.get("availability"));
		if (availability == 0 || availability == 1) {
			return productRepository.changeProductAvailability(id, availability);
		}
		return 0;
	}

	public ResponseEntity<?> deleteProduct(Map<String, Integer> req) {
		Integer id = req.get("id");
		if (id == null) {
			return ResponseEntity.badRequest().body("ID is required in the request");
		}

		if (productRepository.existsById(id)) {
			productRepository.deleteById(id);
			return ResponseEntity.ok("Product deleted successfully");
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
