package de.tarent.challenge.store.service;

import de.tarent.challenge.store.model.Product;
import de.tarent.challenge.store.model.ProductDTO;
import de.tarent.challenge.store.repository.ProductRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public Product updateProduct(String sku, ProductDTO productDto) {
        Product productToUpdate = productRepo.findBySku(sku);

            if(productDto.getName() != null) { productToUpdate.setName(productDto.getName()); }
            if(productDto.getPrice() != null) { productToUpdate.setPrice(productDto.getPrice());}
            if(productDto.getEans() != null && !productDto.getEans().isEmpty()) {
                productDto.getEans().forEach(e ->
                        productToUpdate.addEans(e));
            }
            if(productToUpdate.isAvailable() != productDto.isAvailable()) {
                productToUpdate.setAvailable(productDto.isAvailable());
            }
        return productRepo.save(productToUpdate);
    }
}

