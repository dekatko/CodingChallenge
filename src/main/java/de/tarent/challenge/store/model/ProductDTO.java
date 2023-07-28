package de.tarent.challenge.store.model;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;

//Validation in DTO from Spring
public class ProductDTO {
    private String sku;

    @NotNull
    private String name;

    private BigDecimal price;

    private Set<String> eans;

    private boolean available;

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() { return price; }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Set<String> getEans() {
        return eans;
    }

    public void setEans(Set<String> eans) {
        this.eans = eans;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public ProductDTO() {

    }

    public ProductDTO(String sku, String name, Set<String> eans, boolean available) {
        this.sku = sku;
        this.name = name;
        this.eans = eans;
        this.available = available;
    }
}
