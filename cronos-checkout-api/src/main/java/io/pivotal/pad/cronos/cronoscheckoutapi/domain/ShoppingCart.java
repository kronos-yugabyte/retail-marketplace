package io.pivotal.pad.cronos.cronoscheckoutapi.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "shopping_cart")
@Table(name = "shopping_cart")
public class ShoppingCart {

	@EmbeddedId
	private ShoppingCartKey shoppingCartKey;

	@Column(name = "time_added")
	private String time_added;
	
	@Column(name = "quantity")
	private int quantity;
	
	public String getTime_added() {
		return time_added;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setTime_added(String time_added) {
		this.time_added = time_added;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public ShoppingCartKey getShoppingCartKey() {
		return shoppingCartKey;
	}

	public void setShoppingCartKey(ShoppingCartKey shoppingCartKey) {
		this.shoppingCartKey = shoppingCartKey;
	}

}
