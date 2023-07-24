package de.tarent.challenge.store.products;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Sets;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

import static javax.persistence.GenerationType.AUTO;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @Column(nullable = false, updatable = false)
    private String sku;

    @Column(nullable = false)
    private String name;

    @ElementCollection
    @NotEmpty
    private Set<String> eans;

    public void setSku(String sku) {
        this.sku = sku;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEans(Set<String> eans) {
        this.eans = eans;
    }

    public Product() {
    }

    public Product(String sku, String name, Set<String> eans) {
        this.sku = sku;
        this.name = name;
        this.eans = eans;
    }

    public String getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public Set<String> getEans() {
        return Sets.newHashSet(eans);
    }

    public void addEans(String eans) {
        this.eans.add(eans);
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
