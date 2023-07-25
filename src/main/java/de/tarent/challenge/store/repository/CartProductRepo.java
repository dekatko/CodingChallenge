package de.tarent.challenge.store.repository;

import de.tarent.challenge.store.model.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartProductRepo extends JpaRepository<CartProduct, Long> {

}
