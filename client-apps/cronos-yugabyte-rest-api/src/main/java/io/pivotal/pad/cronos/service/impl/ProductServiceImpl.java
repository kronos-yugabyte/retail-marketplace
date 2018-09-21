package io.pivotal.pad.cronos.service.impl;

import io.pivotal.pad.cronos.domain.ProductMetadata;
import io.pivotal.pad.cronos.repo.ProductMetadataRepo;
import io.pivotal.pad.cronos.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductMetadataRepo productRepository;

    @Autowired
    public ProductServiceImpl(ProductMetadataRepo productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Optional<ProductMetadata> findById(String id) {
        return productRepository.findById(id);
    }

	@Override
	public List<ProductMetadata> findAllProductsPageable(int size) {
		
		return productRepository.getProducts(size);
	}
}
