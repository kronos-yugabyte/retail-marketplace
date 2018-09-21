package io.pivotal.pad.cronos.service;

import java.util.Optional;

import io.pivotal.pad.cronos.domain.ProductRanking;

public interface ProductRankingService {

	Optional<ProductRanking> findProductRankingById(String asin);
	
}
