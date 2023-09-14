package de.tarent.challenge.store.model;

import java.util.List;

public class CartDTO {
    private List<CartProduct> cartProducts;

    private Customer customer;

    private boolean checkedOut;

    public CartDTO() {

    }

    public CartDTO(List<CartProduct> cartProducts, Customer customer) {
        this.cartProducts = cartProducts;
        this.customer = customer;
    }

    public List<CartProduct> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(List<CartProduct> cartProducts) {
        this.cartProducts = cartProducts;
    }

    public Customer getUser() {
        return customer;
    }

    public void setUser(Customer customer) {
        this.customer = customer;
    }

    public boolean isCheckedOut() {
        return checkedOut;
    }

    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }
}
