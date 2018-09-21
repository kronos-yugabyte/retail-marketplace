package io.pivotal.pad.cronos.service;

import java.util.Optional;

import io.pivotal.pad.cronos.domain.*;

public interface ProductInventoryService {

    Optional<ProductInventory> findById(String id);

}
