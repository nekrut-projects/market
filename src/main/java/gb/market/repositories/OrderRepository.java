package gb.market.repositories;

import gb.market.model.Order;
import gb.market.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);

    @EntityGraph(value = "Order.with-items-and-products")
    @Query("SELECT o FROM Order o WHERE o.user.name=:username")
    List<Order> findByUsername(String username);
}
