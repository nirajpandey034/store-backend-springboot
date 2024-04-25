package storeBackend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.transaction.Transactional;
import storeBackend.entity.OrderUser;

public interface OrderUserRepository extends JpaRepository<OrderUser, Integer> {

    @Transactional
    public List<OrderUser> findAllByUserId(Integer userId);
}
