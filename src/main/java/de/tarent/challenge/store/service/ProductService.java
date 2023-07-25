package de.tarent.challenge.store.service;

import de.tarent.challenge.store.model.Product;
import de.tarent.challenge.store.repository.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public List<Product> retrieveAllProducts() {
        return productRepo.findAll();
    }

    public Product retrieveProductBySku(String sku) {
        return productRepo.findBySku(sku);
    }
}
