package de.tarent.challenge.store.repository;

import de.tarent.challenge.store.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartRepo extends JpaRepository<Cart, Long> {

    @Query("SELECT c FROM Cart c WHERE c.customer.username = :#{#username}")
    Cart findCartByCustomerName(@Param("username") String username);


}
