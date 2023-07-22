package de.tarent.challenge.store;

import de.tarent.challenge.store.products.Product;
import de.tarent.challenge.store.products.ProductCatalog;
import de.tarent.challenge.store.products.ProductController;
import de.tarent.challenge.store.products.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class StoreApplicationTests {

    @Autowired
    ProductCatalog productCatalog;

    @Before
    public void setup() {
        Set<String> eans = new HashSet<>();
		eans.add("1234567890");

        Product product = new Product("1010", "test", eans);
        productCatalog.save(product);

    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void createProductTest() {

    }

    @Test
    public void updateProductTest() {
		assertNotNull(productCatalog.findAll());

        ProductController productController = new ProductController(new ProductService(productCatalog));

        String eanForUpdate = "010101010101";
        productController.updateEans("1010", eanForUpdate);
        Product product = productController.retrieveProductBySku("1010");

        assertTrue(product.getEans().contains(eanForUpdate));
    }
}
