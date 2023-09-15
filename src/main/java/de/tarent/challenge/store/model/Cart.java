package de.tarent.challenge.store.model;

import jakarta.persistence.*;
import javax.validation.constraints.NotNull;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static jakarta.persistence.GenerationType.AUTO;


@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @OneToMany(mappedBy = "pk.cart")
    private List<CartProduct> cartProducts;

    @OneToOne
    @NotNull
    private Customer customer;

    @Column(nullable = false)
    private boolean checkedOut;

    @Transient
    public BigDecimal getTotalCartPrice() {
        List<CartProduct> cartProducts = getCartProducts();
        return cartProducts.stream().map(CartProduct::getTotalPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void setCartProducts(List<CartProduct> cartProducts) {
        this.cartProducts = cartProducts;
    }

    public void setUser(Customer customer) {
        this.customer = customer;
    }

    public List<CartProduct> getCartProducts() {
        return cartProducts;
    }

    public Customer getUser() {
        return customer;
    }

    public boolean isCheckedOut() {
        return checkedOut;
    }

    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }

    public Cart() {
    }

    public Cart(List<CartProduct> cartProducts, Customer customer, boolean checkedOut) {
        this.cartProducts = cartProducts;
        this.customer = customer;
        this.checkedOut = checkedOut;
    }
}
