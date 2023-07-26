package de.tarent.challenge.store.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


import java.math.BigDecimal;
import java.util.List;

import static javax.persistence.GenerationType.AUTO;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @OneToMany(mappedBy = "pk.cart")
    @NotEmpty
    private List<CartProduct> cartProducts;

    @OneToOne
    @NotNull
    private User user;

    @Column(nullable = false)
    private boolean checkedOut;

    @Transient
    public BigDecimal getTotalCartPrice() {
        List<CartProduct> cartProducts = getCartProducts();
        return cartProducts.stream().map(cp -> cp.getTotalPrice()).reduce(BigDecimal.ZERO, (a, b) -> a.add(b));
    }

    public void setCartProducts(List<CartProduct> cartProducts) {
        this.cartProducts = cartProducts;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CartProduct> getCartProducts() {
        return cartProducts;
    }

    public User getUser() {
        return user;
    }

    public boolean isCheckedOut() {
        return checkedOut;
    }

    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }
}
