package storeBackend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import storeBackend.entity.OrderUser;

public interface OrderUserRepository extends JpaRepository<OrderUser, Integer> {

}
