package de.tarent.challenge.store.service;

import de.tarent.challenge.store.model.Cart;
import de.tarent.challenge.store.model.CartDTO;
import org.springframework.stereotype.Component;

@Component
public class CartMapper {
    public CartDTO toDto(Cart cart) {
        return new CartDTO(cart.getCartProducts(), cart.getUser());
    }

    public Cart toProduct(CartDTO cartDto) {
        return new Cart(cartDto.getCartProducts(), cartDto.getUser(), cartDto.isCheckedOut());
    }
}
