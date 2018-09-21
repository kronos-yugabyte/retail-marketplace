package io.pivotal.pad.cronos.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import io.pivotal.pad.cronos.domain.ProductRanking;
import io.pivotal.pad.cronos.repo.ProductRankingRepository;
import io.pivotal.pad.cronos.service.ProductRankingService;

public class ProductRankingServiceImpl implements ProductRankingService{
	
	private final ProductRankingRepository productRankingRepository;
	
	@Autowired
	public ProductRankingServiceImpl(ProductRankingRepository productRankingRepository) {
		this.productRankingRepository = productRankingRepository;
	}

	@Override
	public Optional<ProductRanking> findProductRankingById(String asin) {
		
		return productRankingRepository.findProductRankingById(asin);
	}

}
