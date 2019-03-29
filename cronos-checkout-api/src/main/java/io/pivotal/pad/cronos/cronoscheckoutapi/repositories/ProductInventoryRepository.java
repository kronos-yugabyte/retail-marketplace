package io.pivotal.pad.cronos.cronoscheckoutapi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.pivotal.pad.cronos.cronoscheckoutapi.domain.ProductInventory;

@Repository
public interface ProductInventoryRepository extends CrudRepository<ProductInventory, String> {

}
