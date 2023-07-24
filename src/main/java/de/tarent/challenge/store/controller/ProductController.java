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
        Product productToUpdate = productCatalogRepository.findBySku(sku);
        if(productToUpdate != null) {
            if(productUpdateDto.getName() != null) { productToUpdate.setName(productUpdateDto.getName()); }
            if(productUpdateDto.getPrice() != null) { productToUpdate.setPrice(productUpdateDto.getPrice());}
            if(productUpdateDto.getEans() != null && !productUpdateDto.getEans().isEmpty()) {
                productUpdateDto.getEans().stream().forEach(e ->
                        productToUpdate.addEans(e));
            }

        } else {
            ResponseEntity.notFound();
        }
        productCatalogRepository.save(productToUpdate);
        return ResponseEntity.ok(productToUpdate);
    }

    @PostMapping("/create-product")
    public ResponseEntity<Product> createProduct(@RequestBody ProductDTO productUpdateDto) {
        Product product = new Product();
        product.setName(productUpdateDto.getName());
        product.setPrice(productUpdateDto.getPrice());
        product.setSku(productUpdateDto.getSku());
        product.setEans(productUpdateDto.getEans());

        productCatalogRepository.save(product);

        return ResponseEntity.ok(product);
    }
}
