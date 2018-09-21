package io.pivotal.pad.cronos.repo;

import java.util.Optional;

import org.springframework.data.cassandra.repository.CassandraRepository;

import io.pivotal.pad.cronos.domain.ProductRanking;

public interface ProductRankingRepository extends CassandraRepository<ProductRanking, String> {
	
	Optional<ProductRanking> findProductRankingById(String asin);

}