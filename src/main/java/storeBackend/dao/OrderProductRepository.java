package storeBackend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import storeBackend.entity.OrderProduct;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Integer> {

}
