package de.tarent.challenge.store.controller;

import de.tarent.challenge.store.model.Cart;
import de.tarent.challenge.store.model.UserDTO;
import de.tarent.challenge.store.repository.CartProductRepo;
import de.tarent.challenge.store.repository.CartRepo;
import de.tarent.challenge.store.repository.ProductRepo;
import de.tarent.challenge.store.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/carts")
public class CartController {

    private final CartRepo cartRepo;

    private final CartProductRepo cartProductRepo;

    private final ProductRepo productRepo;

    private final CartService cartService;

    public CartController(CartRepo cartRepo, CartProductRepo cartProductRepo, ProductRepo productRepo, CartService cartService) {
        this.cartRepo = cartRepo;
        this.cartProductRepo = cartProductRepo;
        this.productRepo = productRepo;
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Cart>> retrieveCarts() {
        return ResponseEntity.ok(cartService.retrieveAllCarts());
    }

    @GetMapping("/{username}")
    public ResponseEntity getCurrentCart(@PathVariable String username) {
        Cart cart = cartService.retrieveCartByUserName(username);
        if (cart != null) {
            return ResponseEntity.ok(cart);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This user does not have a cart yet");
    }

    @PostMapping
    public ResponseEntity createCart(@RequestBody UserDTO userDTO) {
        return cartService.createCart(userDTO.getUsername());
    }

    @PutMapping("/{sku}/{quantity}")
    public ResponseEntity updateCart(@PathVariable String sku, @PathVariable Integer quantity, @RequestBody UserDTO user) {
        return cartService.updateCart(sku, quantity, user);
    }

    @PutMapping
    public ResponseEntity checkoutCart(@RequestBody UserDTO user) {
        return cartService.checkoutCart(user);
    }

}
