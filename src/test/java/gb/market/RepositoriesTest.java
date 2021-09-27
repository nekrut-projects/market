package gb.market;

import gb.market.model.Order;
import gb.market.repositories.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
public class RepositoriesTest {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void getOrdersByUsernameTest() {
        List<Order> orderList = orderRepository.findByUsername("test");
        Assertions.assertEquals(3, orderList.size());
        Assertions.assertEquals("Address_3", orderList.get(2).getAddress());
    }

}
