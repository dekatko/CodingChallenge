package de.tarent.challenge.store.service;

import de.tarent.challenge.store.model.Product;
import de.tarent.challenge.store.model.ProductDTO;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductDTO toDto(Product product) {
        return new ProductDTO(product.getSku(), product.getName(), product.getEans(), product.isAvailable());
    }

    public Product toProduct(ProductDTO productDto) {
        return new Product(productDto.getSku(), productDto.getName(), productDto.getPrice(), productDto.getEans(), productDto.isAvailable());
    }
}
