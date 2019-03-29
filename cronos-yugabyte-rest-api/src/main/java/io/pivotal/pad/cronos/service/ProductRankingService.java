package io.pivotal.pad.cronos.service;

import java.util.List;
import java.util.Optional;

import io.pivotal.pad.cronos.domain.ProductRanking;

public interface ProductRankingService {

	Optional<ProductRanking> findProductRankingById(String asin);
	
	List<ProductRanking> getProductsByCategory(String category, int limit, int offset);
	
}
