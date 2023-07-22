package de.tarent.challenge.store.controller;

import de.tarent.challenge.store.products.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductCatalog productCatalogRepository;
    private final ProductMapper productMapper;
    private final ProductService productService;

    public ProductController(ProductCatalog productCatalogRepository, ProductMapper productMapper, ProductService productService) {
        this.productCatalogRepository = productCatalogRepository;
        this.productMapper = productMapper;
        this.productService = productService;
    }

    @GetMapping
    public Iterable<Product> retrieveProducts() {
        return productService.retrieveAllProducts();
    }

    @GetMapping("/{sku}")
    public Product retrieveProductBySku(@PathVariable String sku) {
        return productService.retrieveProductBySku(sku);
    }

    @PutMapping("/update-product/{sku}")
    public ResponseEntity<Product> updateProduct(@PathVariable String sku, @RequestBody ProductDTO productUpdateDto) {
        Product persisted = productCatalogRepository.findBySku(sku);
        Product productUpdate = productMapper.toProduct(productUpdateDto);
        if(persisted != null) {
            if(productUpdateDto.getName() != null){ persisted.setName(productUpdateDto.getName()); }

            if(productUpdateDto.getEans() != null && !productUpdateDto.getEans().isEmpty()) { productUpdateDto.getEans().stream().forEach(e -> persisted.addEans(e)); }

        } else {
            ResponseEntity.notFound();
        }
        productCatalogRepository.save(persisted);
        return ResponseEntity.ok(persisted);
    }
}
