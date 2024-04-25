package storeBackend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import storeBackend.entity.OrderProduct;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Integer> {

    @Modifying
    @Transactional
    @Query("SELECT op FROM OrderProduct op WHERE op.order_id IN :orderIds")
    List<OrderProduct> findByOrderIdIn(@Param("orderIds") List<Integer> orderList);
}
