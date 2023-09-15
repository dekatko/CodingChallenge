package de.tarent.challenge.store.service;

import de.tarent.challenge.store.model.*;
import de.tarent.challenge.store.repository.CartProductRepo;
import de.tarent.challenge.store.repository.CartRepo;
import de.tarent.challenge.store.repository.ProductRepo;
import de.tarent.challenge.store.repository.UserRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartService {

    private final CartRepo cartRepo;

    private final UserRepo userRepo;

    private final ProductRepo productRepo;

    private final CartProductRepo cartProductRepo;

    public CartService(CartRepo cartRepo, UserRepo userRepo, ProductRepo productRepo, CartProductRepo cartProductRepo) {
        this.cartRepo = cartRepo;
        this.userRepo = userRepo;
        this.productRepo = productRepo;
        this.cartProductRepo = cartProductRepo;
    }

    public List<Cart> retrieveAllCarts() {
        return cartRepo.findAll();
    }

    public Cart retrieveCartByCustomerName(String username) {
        return cartRepo.findCartByCustomerName(username);
    }

    public ResponseEntity createCart(String username) {
        Customer customer = userRepo.findUserByUsername(username);
        Cart existingCart = cartRepo.findCartByCustomerName(username);
        if (existingCart == null) {
            List<CartProduct> cartProductList = new ArrayList<>();
            Cart cart = new Cart();
            cart.setUser(customer);
            cart.setCheckedOut(false);
            cart.setCartProducts(cartProductList);

            return ResponseEntity.ok(cartRepo.save(cart));
        }
        //According to RFC 7231, a 303 See Other MAY be used If the result of processing a POST would be equivalent to a representation of an existing resource.
        return ResponseEntity.status(HttpStatus.SEE_OTHER).body(existingCart);
    }

    //Implement removal of product
    public ResponseEntity updateCart(String sku, Integer quantity, UserDTO user) {
        Cart cartToUpdate = cartRepo.findCartByCustomerName(user.getUsername());
        Product product = productRepo.findBySku(sku);

        //Produkt bei zweitem Update Menge erhöhen, nicht neues Produkt hinzufügen
        if (cartToUpdate.isCheckedOut()) {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("Cart cannot be updated. Cart is already checked out.");
        } else if (product.isAvailable() && !productAlreadyInCart(cartToUpdate.getCartProducts(), sku)) {
            cartToUpdate.getCartProducts().add(cartProductRepo.save(new CartProduct(cartToUpdate, product, quantity)));
            //Ganzen Cart zurückgeben
            return ResponseEntity.ok(cartRepo.save(cartToUpdate));
        } else if(product.isAvailable() && productAlreadyInCart(cartToUpdate.getCartProducts(), sku)) {
            updateQuantityOfProductInCart(cartToUpdate, sku, quantity);
            return ResponseEntity.ok(cartRepo.save(cartToUpdate));
        } else {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Product not available");
        }
    }

    //Nach Checkout KEINE Preisänderung!
    public ResponseEntity checkoutCart(UserDTO user) {
        Cart cartToCheckout = cartRepo.findCartByCustomerName(user.getUsername());

        if (!cartToCheckout.isCheckedOut()) {
            cartToCheckout.setCheckedOut(true);

            return ResponseEntity.ok(cartRepo.save(cartToCheckout));
        }

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("Cart already checked out!");
    }

    private boolean productAlreadyInCart(List<CartProduct> cartProducts, String sku) {
        return cartProducts.stream().anyMatch(c -> c.getProduct().getSku().equals(sku));
    }

    private void updateQuantityOfProductInCart(Cart cart, String sku, int quantity) {
        cart.getCartProducts().stream()
                .filter(cartProduct -> cartProduct.getProduct().getSku().equals(sku))
                .forEach(cartProduct -> cartProduct.increaseQuantity(quantity));
    }
}
