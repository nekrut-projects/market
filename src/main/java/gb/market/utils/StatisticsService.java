package gb.market.utils;

import gb.market.model.Category;
import gb.market.model.Product;
import gb.market.model.User;
import gb.market.services.CategoryService;
import gb.market.services.OrderService;
import gb.market.services.ProductService;
import gb.market.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class StatisticsService {
    private final UserService userService;
    private final ProductService productService;
    private final OrderService orderService;
    private final CategoryService categoryService;

    public void testServices(){
        Product product = productService.save(new Product("test", BigDecimal.valueOf(1L), new Category("Categories_1")));
        productService.findById(product.getId());
        productService.deleteById(product.getId());
        productService.showPage(1, 1, null);

        User user = userService.findByName("test").get();
        orderService.createOrder(user, "1", "1");
        orderService.findByUser(user);
        orderService.findByUsername(user.getName());

        categoryService.findById(1L);
        categoryService.findByTitle("Categories_1");

    }
}
