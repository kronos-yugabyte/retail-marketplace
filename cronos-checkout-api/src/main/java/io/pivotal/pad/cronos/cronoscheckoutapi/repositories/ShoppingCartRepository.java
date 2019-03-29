package io.pivotal.pad.cronos.cronoscheckoutapi.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import io.pivotal.pad.cronos.cronoscheckoutapi.domain.ShoppingCart;
import io.pivotal.pad.cronos.cronoscheckoutapi.domain.ShoppingCartKey;

public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, ShoppingCartKey> {
	
	//@Modifying
	@Transactional(readOnly = true)
	@Query("UPDATE shopping_cart SET quantity = quantity + 1 WHERE user_id = ?1 AND asin = ?2")
	int updateQuantityForShoppingCart(String userId, String asin);

}
