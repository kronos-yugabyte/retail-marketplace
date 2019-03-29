package io.pivotal.pad.cronos.repo;

import java.util.Optional;

import org.springframework.data.cassandra.repository.CassandraRepository;

import io.pivotal.pad.cronos.domain.ProductInventory;

public interface ProductInventoryRepository extends CassandraRepository<ProductInventory, String> {
	Optional<ProductInventory> findById(String id);
}
