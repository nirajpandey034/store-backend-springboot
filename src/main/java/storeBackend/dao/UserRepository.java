package storeBackend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import storeBackend.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Modifying
	@Transactional
	@Query("UPDATE User u SET u.phone = :phone WHERE u.id = :id")
	public int changeUserPhone(@Param("id") Integer id, @Param("phone") String phone);
	
}
