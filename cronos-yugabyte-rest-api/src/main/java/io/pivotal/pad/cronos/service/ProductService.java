package io.pivotal.pad.cronos.service;

import java.util.List;
import java.util.Optional;

import io.pivotal.pad.cronos.domain.*;

public interface ProductService {

    Optional<ProductMetadata> findById(String id);

    List<ProductMetadata> findAllProductsPageable(int limit, int offset);

}
