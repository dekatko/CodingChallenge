package de.tarent.challenge.store.model;

import java.util.List;

public class CartDTO {
    private List<CartProduct> cartProducts;

    private User user;

    public CartDTO() {

    }

    public CartDTO(List<CartProduct> cartProducts, User user) {
        this.cartProducts = cartProducts;
        this.user = user;
    }

    public List<CartProduct> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(List<CartProduct> cartProducts) {
        this.cartProducts = cartProducts;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
