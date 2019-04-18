package io.pivotal.pad.cronos.cronoscheckoutapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import io.pivotal.pad.cronos.cronoscheckoutapi.domain.ShoppingCart;


@RepositoryRestResource
public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, String> {
	
	@Modifying
	@Transactional
	@Query("UPDATE shopping_cart SET quantity = quantity + 1 WHERE user_id = ?1 AND asin =?2")
	int updateQuantityForShoppingCart(String userId, String asin);
	
	@Query("SELECT quantity FROM shopping_cart WHERE user_id = ?1 AND asin = ?2")
	Optional<Integer> findByUserIdAndAsin(String userId, String asin);

}
