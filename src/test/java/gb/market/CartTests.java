package gb.market;

import gb.market.dto.ProductDto;
import gb.market.services.ProductService;
import gb.market.utils.Cart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = Cart.class)
public class CartTests {
    @Autowired
    private Cart cart;

    @MockBean
    private ProductService productService;

    private ProductDto productTest;

    @BeforeEach
    public void initCart(){
        cart.clear();

        productTest = new ProductDto();
        productTest.setId(1L);
        productTest.setTitle("Test product");
        productTest.setPrice(BigDecimal.valueOf(333.0));
        productTest.setCategory("Test category");
    }

    @Test
    public void deleteItemTest(){
        cart.add(productTest);
        cart.delItem(productTest.getId());
        assertEquals(0, cart.getItems().size());
    }

    @Test
    public  void recalculateTest(){
        cart.add(productTest);
        cart.add(productTest);
        cart.add(productTest);
        assertEquals(BigDecimal.valueOf(999.0), cart.getPrice());
    }
}
