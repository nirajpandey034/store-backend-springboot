package storeBackend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import storeBackend.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Modifying
	@Transactional
	@Query("UPDATE Product p SET p.title = :title WHERE p.id = :id")
	public int changeProductTitle(@Param("id") Integer id, @Param("title") String title);

	@Modifying
	@Transactional
	@Query("UPDATE Product p SET p.price = :price WHERE p.id = :id")
	public int changeProductPrice(@Param("id") Integer id, @Param("price") String price);

	@Modifying
	@Transactional
	@Query("UPDATE Product p SET p.availability = :availability WHERE p.id = :id")
	public int changeProductAvailability(@Param("id") Integer id, @Param("availability") Integer availability);
}
