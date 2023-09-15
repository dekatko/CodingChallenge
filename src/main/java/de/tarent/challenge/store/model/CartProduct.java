package de.tarent.challenge.store.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class CartProduct {

    @EmbeddedId
    @JsonIgnore
    private CartProductPK pk;

    @Column(nullable = false)
    private Integer quantity;

    public CartProduct() {

    }

    public CartProduct(Cart cart, Product product, Integer quantity) {
        pk = new CartProductPK();
        pk.setCart(cart);
        pk.setProduct(product);
        this.quantity = quantity;
    }

    @Transient
    public Product getProduct() { return this.pk.getProduct(); }

    @Transient
    public BigDecimal getTotalPrice() {
        return getProduct().getPrice().multiply(BigDecimal.valueOf(getQuantity()));
    }

    public CartProductPK getPk() {
        return pk;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public void increaseQuantity(Integer quantity) { this.quantity += quantity; }

    public void decreaseQuantity(Integer quantity) { this.quantity -= quantity; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartProduct that = (CartProduct) o;
        return pk.equals(that.pk) && quantity.equals(that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pk, quantity);
    }
}
