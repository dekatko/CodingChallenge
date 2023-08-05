package de.tarent.challenge.store.controller;

import de.tarent.challenge.store.model.Product;
import de.tarent.challenge.store.model.ProductDTO;
import de.tarent.challenge.store.repository.ProductRepo;
import de.tarent.challenge.store.service.ProductService;
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

    @PutMapping("/{sku}")
    public ResponseEntity updateProduct(@PathVariable String sku, @RequestBody ProductDTO productUpdateDto) {
        Product productToUpdate = productRepoRepository.findBySku(sku);
        if (productToUpdate == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        } else if (!productToUpdate.isAvailable()) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Product not available");
        } else {
            return ResponseEntity.ok(productService.updateProduct(sku, productUpdateDto));
        }
    }

    @PostMapping
    public ResponseEntity createProduct(@RequestBody ProductDTO productUpdateDto) {
        if (productRepoRepository.findBySku(productUpdateDto.getSku()) != null) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Product Sku already exists");
        } else {
            return ResponseEntity.ok(productService.createProduct(productUpdateDto));
        }
    }
}
