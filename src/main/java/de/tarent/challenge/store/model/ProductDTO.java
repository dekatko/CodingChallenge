package de.tarent.challenge.store.model;

import java.math.BigDecimal;
import java.util.Set;


public class ProductDTO {
    private String sku;

    private String name;

    private BigDecimal price;

    private Set<String> eans;

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

    public ProductDTO() {

    }

    public ProductDTO(String sku, String name, Set<String> eans) {
        this.sku = sku;
        this.name = name;
        this.eans = eans;
    }
}
