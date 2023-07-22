package de.tarent.challenge.store.products;

import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductDTO toDto(Product product) {
        return new ProductDTO(product.getSku(), product.getName(), product.getEans());
    }

    public Product toProduct(ProductDTO productDto) {
        return new Product(productDto.getSku(), productDto.getName(), productDto.getEans());
    }
}
