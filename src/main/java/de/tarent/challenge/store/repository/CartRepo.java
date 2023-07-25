package de.tarent.challenge.store.repository;

import de.tarent.challenge.store.model.Cart;
import de.tarent.challenge.store.model.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartRepo extends JpaRepository<Cart, Long> {

    @Query("SELECT c FROM Cart c WHERE c.user.username = :#{#user.username}")
    Cart findCartByUser(@Param("user") UserDTO user);

}
