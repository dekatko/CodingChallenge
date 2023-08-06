package de.tarent.challenge.store.service;

import de.tarent.challenge.store.model.Cart;
import de.tarent.challenge.store.model.CartProduct;
import de.tarent.challenge.store.model.User;
import de.tarent.challenge.store.repository.CartRepo;
import de.tarent.challenge.store.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    private final CartRepo cartRepo;

    private final UserRepo userRepo;

    public CartService(CartRepo cartRepo, UserRepo userRepo) {
        this.cartRepo = cartRepo;
        this.userRepo = userRepo;
    }

    public List<Cart> retrieveAllCarts() {
        return cartRepo.findAll();
    }

    public Cart retrieveCartByUserName(String username) {
        return cartRepo.findCartByUserName(username);
    }

    public Cart createCart(String username) {
        User user = userRepo.findUserByUsername(username);
        if (cartRepo.findCartByUserName(username) == null) {
            List<CartProduct> cartProductList = new ArrayList<>();
            Cart cart = new Cart();
            cart.setUser(user);
            cart.setCheckedOut(false);
            cart.setCartProducts(cartProductList);

            return cartRepo.save(cart);
        }
        return null;
    }
}
