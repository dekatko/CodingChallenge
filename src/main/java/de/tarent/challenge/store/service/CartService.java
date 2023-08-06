package de.tarent.challenge.store.service;

import de.tarent.challenge.store.model.Cart;
import de.tarent.challenge.store.repository.CartRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartRepo cartRepo;

    public CartService(CartRepo cartRepo) {
        this.cartRepo = cartRepo;
    }

    public List<Cart> retrieveAllCarts() {
        return cartRepo.findAll();
    }

    public Cart retrieveCartByUserName(String username) {
        return cartRepo.findCartByUserName(username);
    }


}
