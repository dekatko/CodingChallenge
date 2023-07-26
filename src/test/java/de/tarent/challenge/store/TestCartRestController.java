package de.tarent.challenge.store;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
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
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class TestCartRestController {

    public static final String BASE_PATH = "/carts/";
    @Autowired
    MockMvc mvc;

    @Test
    public void contextLoads() {
    }

    @Test
    public void retrieveCartsTest() throws Exception{
        mvc.perform(MockMvcRequestBuilders
                .get(BASE_PATH))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].user").exists())
                .andExpect(jsonPath("$.[0].checkedOut").exists())
                .andExpect(jsonPath("$.[0].cartProducts[0].quantity").exists())
                .andExpect(jsonPath("$.[0].cartProducts[0].totalPrice").exists())
                .andExpect(jsonPath("$.[0].cartProducts[0].product").exists());
    }

    @Test
    public void retrieveCurrentCartTest() throws Exception{
        String jsonForBody = "{\"username\": \"denis-the-menace\"}";

        mvc.perform(MockMvcRequestBuilders
                        .get(BASE_PATH + "current-cart")
                        .content(jsonForBody).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user").exists())
                .andExpect(jsonPath("$.user.username").value("denis-the-menace"))
                .andExpect(jsonPath("$.checkedOut").exists())
                .andExpect(jsonPath("$.cartProducts[0].quantity").exists())
                .andExpect(jsonPath("$.cartProducts[0].totalPrice").value("3.98"));
    }

    @Test
    public void updateCartTest() throws Exception {
        String jsonForBody = "{\"username\": \"denis-the-menace\"}";

        mvc.perform(put(BASE_PATH + "update-cart/B001/3")
                .content(jsonForBody)
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cartProducts[2].quantity").value("3"))
                .andExpect(jsonPath("$.cartProducts[2].product.name").value("Couscous"));
    }

    @Test
    public void createCartTest() throws Exception {
        String jsonForBody = "{\"user\": {\"username\": \"Yedi-Tester\"}}";

        mvc.perform(post(BASE_PATH + "create-cart/B001/8")
                        .content(jsonForBody)
                        .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cartProducts[0].quantity").value("8"))
                .andExpect(jsonPath("$.cartProducts[0].product.name").value("Couscous"));
    }

    @Test
    public void checkoutCartTest() throws Exception {
        String jsonForBody = "{\"username\": \"denis-the-menace\"}";

        mvc.perform(put(BASE_PATH + "checkout-cart")
                        .content(jsonForBody)
                        .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.checkedOut").exists())
                .andExpect(jsonPath("$.checkedOut").value("true"))
                .andDo(result -> mvc.perform(put(BASE_PATH + "checkout-cart")
                                .content(jsonForBody)
                                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().is4xxClientError()));
    }
}
