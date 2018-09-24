package io.pivotal.pad.cronos.repo;

import java.util.Optional;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import io.pivotal.pad.cronos.domain.ProductRanking;

public interface ProductRankingRepository extends CassandraRepository<ProductRanking, String> {
	
	@Query("select * from cronos.product_rankings where asin=?0")
	Optional<ProductRanking> findProductRankingById(String asin);

}