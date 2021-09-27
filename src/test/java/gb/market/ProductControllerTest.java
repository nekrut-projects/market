package gb.market;

import gb.market.model.Category;
import gb.market.model.Product;
import gb.market.services.CategoryService;
import gb.market.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private CategoryService categoryService;

    @Test
    public void findByIdTest() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setPrice(BigDecimal.valueOf(111));
        product.setTitle("test_product");
        product.setCategory(new Category("test_category"));

        given(productService.findById(1L)).willReturn(Optional.of(product));

        mvc.
                perform(
                        get("/api/v1/products/1")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is(product.getTitle())));
    }

}
