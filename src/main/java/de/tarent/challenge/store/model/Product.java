package de.tarent.challenge.store.model;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Sets;
import org.hibernate.validator.constraints.NotEmpty;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @Column(nullable = false, updatable = false, unique = true)
    private String sku;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Min(value = 0)
    private BigDecimal price;

    @ElementCollection
    @NotEmpty
    private Set<String> eans;

    @Column(nullable = false)
    private boolean available;

    public Product() {
    }

    public Product(String sku, String name, BigDecimal price, Set<String> eans, boolean available) {
        this.sku = sku;
        this.name = name;
        this.price = price;
        this.eans = eans;
        this.available = available;
    }

    public String getSku() {
        return sku;
    }

    public String getName() { return name; }

    public BigDecimal getPrice() { return price; }

    public Set<String> getEans() {
        return Sets.newHashSet(eans);
    }


    public void setSku(String sku) { this.sku = sku; }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setEans(Set<String> eans) { this.eans = eans; }

    public void addEans(String eans) {
        this.eans.add(eans);
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(sku, product.sku) &&
                Objects.equals(name, product.name) &&
                Objects.equals(eans, product.eans);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sku, name, eans);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("sku", sku)
                .add("name", name)
                .add("eans", eans)
                .toString();
    }
}
