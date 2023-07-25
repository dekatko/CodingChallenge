package de.tarent.challenge.store.controller;

import de.tarent.challenge.store.model.*;
import de.tarent.challenge.store.repository.CartProductRepo;
import de.tarent.challenge.store.repository.CartRepo;
import de.tarent.challenge.store.repository.ProductRepo;
import de.tarent.challenge.store.repository.UserRepo;
import de.tarent.challenge.store.service.CartService;
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
    public ResponseEntity<Cart> updateCart(@PathVariable String sku, @PathVariable Integer quantity, @RequestBody UserDTO user) {
        Cart cartToUpdate = cartRepo.findCartByUser(user);
        Product product = productRepo.findBySku(sku);

        cartToUpdate.getCartProducts().add(cartProductRepo.save(new CartProduct(cartToUpdate, product, quantity)));

        cartRepo.save(cartToUpdate);

        return ResponseEntity.ok(cartToUpdate);
    }


}