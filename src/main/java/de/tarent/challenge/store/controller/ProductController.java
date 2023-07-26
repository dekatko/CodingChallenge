package de.tarent.challenge.store.controller;

import de.tarent.challenge.store.model.Product;
import de.tarent.challenge.store.model.ProductDTO;
import de.tarent.challenge.store.service.*;
import de.tarent.challenge.store.repository.ProductRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductRepo productRepoRepository;
    private final ProductService productService;

    public ProductController(ProductRepo productRepoRepository, ProductService productService) {
        this.productRepoRepository = productRepoRepository;
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
    public ResponseEntity updateProduct(@PathVariable String sku, @RequestBody ProductDTO productUpdateDto) {
        Product productToUpdate = productRepoRepository.findBySku(sku);
        if(productToUpdate == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        } else if (!productToUpdate.isAvailable()) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Product not available");
        } else {
                if(productUpdateDto.getName() != null) { productToUpdate.setName(productUpdateDto.getName()); }
                if(productUpdateDto.getPrice() != null) { productToUpdate.setPrice(productUpdateDto.getPrice());}
                if(productUpdateDto.getEans() != null && !productUpdateDto.getEans().isEmpty()) {
                    productUpdateDto.getEans().forEach(e ->
                            productToUpdate.addEans(e));
                }
                if(productToUpdate.isAvailable() != productUpdateDto.isAvailable()) {
                    productToUpdate.setAvailable(productUpdateDto.isAvailable());
                }
            }

        productRepoRepository.save(productToUpdate);
        return ResponseEntity.ok(productToUpdate);
    }

    @PostMapping("/create-product")
    public ResponseEntity<Product> createProduct(@RequestBody ProductDTO productUpdateDto) {
        Product product = new Product();
        product.setName(productUpdateDto.getName());
        product.setPrice(productUpdateDto.getPrice());
        product.setSku(productUpdateDto.getSku());
        product.setEans(productUpdateDto.getEans());

        productRepoRepository.save(product);

        return ResponseEntity.ok(product);
    }
}
