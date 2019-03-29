package io.pivotal.pad.cronos.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.pivotal.pad.cronos.domain.ProductInventory;
import io.pivotal.pad.cronos.repo.ProductInventoryRepository;
import io.pivotal.pad.cronos.service.ProductInventoryService;

@Service
public class ProductInventoryServiceImpl implements ProductInventoryService {

    private final ProductInventoryRepository productInventoryRepository;

    @Autowired
    public ProductInventoryServiceImpl(ProductInventoryRepository productInventoryRepository) {
        this.productInventoryRepository = productInventoryRepository;
    }

    @Override
    public Optional<ProductInventory> findById(String id) {
        return productInventoryRepository.findById(id);
    }
}
