package de.tarent.challenge.store.products;

import java.util.Set;


public class ProductDTO {
    private String sku;

    private String name;

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
