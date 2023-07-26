package de.tarent.challenge.store;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestProductRestController {

    public static final String BASE_PATH = "/products/";
    @Autowired
    MockMvc mvc;

    @Test
    public void contextLoads() {
    }

    @Test
    public void retrieveProductsTest() throws Exception{
        mvc.perform(MockMvcRequestBuilders
                .get(BASE_PATH))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].sku").exists())
                .andExpect(jsonPath("$.[0].name").exists())
                .andExpect(jsonPath("$.[0].price").exists());
    }

    @Test
    public void retrieveProductBySkuTest() throws Exception{
        mvc.perform(MockMvcRequestBuilders
                        .get(BASE_PATH + "102"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sku").exists())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.price").exists())
                .andExpect(jsonPath("$.name").value("Milch"));
    }

    @Test
    public void updateProductTest() throws Exception {
        String jsonForBody = "{\"name\": \"Milk\"}";

        mvc.perform(put(BASE_PATH + "update-product/102")
                .content(jsonForBody)
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Milk"));
    }

    @Test
    public void createProductTest() throws Exception {
        String jsonForBody = "{\"sku\": \"777-777-777\",\"name\": \"Chainsaw\",\"price\": \"6.33\", \"eans\": [\"123-456-789\"]}";

        mvc.perform(post(BASE_PATH + "create-product")
                        .content(jsonForBody)
                        .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sku").value("777-777-777"))
                .andExpect(jsonPath("$.name").value("Chainsaw"))
                .andExpect(jsonPath("$.price").value("6.33"))
                .andExpect(jsonPath("$.eans").isNotEmpty());
    }

    @Test
    public void addUnavailableProductInCartTest() throws Exception {
        String jsonForProductUpdateBody = "{\"available\": \"true\"}";
        String jsonForCartUpdateBody = "{\"username\": \"denis-the-menace\"}";

        mvc.perform(put(BASE_PATH + "update-product/102")
                        .content(jsonForProductUpdateBody)
                        .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.available").value("true"))
                .andDo(result ->


        mvc.perform(put(BASE_PATH + "update-cart/102/3")
                        .content(jsonForCartUpdateBody)
                        .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError()));
    }
}
