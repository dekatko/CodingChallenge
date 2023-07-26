package de.tarent.challenge.store.controller;

import de.tarent.challenge.store.model.*;
import de.tarent.challenge.store.repository.CartProductRepo;
import de.tarent.challenge.store.repository.CartRepo;
import de.tarent.challenge.store.repository.ProductRepo;
import de.tarent.challenge.store.repository.UserRepo;
import de.tarent.challenge.store.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartRepo cartRepo;

    private final CartProductRepo cartProductRepo;

    private final UserRepo userRepo;

    private final ProductRepo productRepo;

    private final CartService cartService;

    public CartController(CartRepo cartRepo, CartProductRepo cartProductRepo, ProductRepo productRepo, UserRepo userRepo, CartService cartService) {
        this.cartRepo = cartRepo;
        this.cartProductRepo = cartProductRepo;
        this.productRepo = productRepo;
        this.userRepo = userRepo;
        this.cartService = cartService;
    }

    @GetMapping
    public Iterable<Cart> retrieveCarts() {
        return cartService.retrieveAllCarts();
    }

    @GetMapping("/current-cart")
    public ResponseEntity<Cart> getCurrentCart(@RequestBody UserDTO user) {
        Cart cart = cartRepo.findCartByUser(user);

        return ResponseEntity.ok(cart);
    }

    @PostMapping("/create-cart")
    public ResponseEntity<Cart> createCart(@RequestBody CartDTO cartDTO) {
        User user = userRepo.findUserByUsername(cartDTO.getUser().getUsername());
        Cart cart = new Cart();
        cart.setUser(user);
        cart.setCartProducts(cartDTO.getCartProducts());

        cartRepo.save(cart);
        return ResponseEntity.ok(cart);
    }

    @PutMapping("/update-cart/{sku}/{quantity}")
    public ResponseEntity updateCart(@PathVariable String sku, @PathVariable Integer quantity, @RequestBody UserDTO user) {
        Cart cartToUpdate = cartRepo.findCartByUser(user);
        Product product = productRepo.findBySku(sku);

        if(cartToUpdate.isCheckedOut()) {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("Cart cannot be updated. Cart is already checked out.");
        } else if(product.isAvailable()) {
            cartToUpdate.getCartProducts().add(cartProductRepo.save(new CartProduct(cartToUpdate, product, quantity)));

            cartRepo.save(cartToUpdate);

            return ResponseEntity.ok(cartToUpdate);
        } else {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Product not available");
        }
    }

    @PutMapping("/checkout-cart")
    public ResponseEntity checkoutCart(@RequestBody UserDTO user) {
        Cart cartToCheckout = cartRepo.findCartByUser(user);

        if(!cartToCheckout.isCheckedOut()) {
            cartToCheckout.setCheckedOut(true);
            cartRepo.save(cartToCheckout);

            return ResponseEntity.ok(cartToCheckout);
        }

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("Cart already checked out!");
    }

}
