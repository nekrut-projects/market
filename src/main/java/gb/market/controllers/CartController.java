package gb.market.controllers;

import gb.market.dto.ProductDto;
import gb.market.utils.Cart;
import lombok.RequiredArgsConstructor;
import gb.market.exceptions.ResourceNotFoundException;
import gb.market.services.OrderItemService;
import gb.market.services.OrderService;
import gb.market.services.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final Cart cart;
    private final ProductService productService;
    private final OrderService orderService;
    private final OrderItemService orderItemService;

    @GetMapping("/del/{id}")
    public void delItemCart(@PathVariable(name = "id") Long id) {
        cart.delItem(id);
    }

    @GetMapping("/clear")
    public void clearCart() {
        cart.clear();
    }

    @GetMapping
    public Cart getCart() {
        return cart;
    }

    @GetMapping("/add/{productId}")
    public void add(@PathVariable Long productId) {
        if (!cart.isContainedProduct(productId)){
            cart.add(new ProductDto(productService.findById(productId)
                    .orElseThrow(() -> new ResourceNotFoundException("Unable add product to cart. Product not found id: " + productId))));
        } else {
            cart.add(productId);
        }
    }

    @GetMapping("/item/{id}/decrease/")
    public void decreaseQuantity(@PathVariable Long id) {
        cart.decreaseQuantity(id);
    }

    @GetMapping("/item/{id}/increase/")
    public void increaseQuantity(@PathVariable Long id) {
        cart.increaseQuantity(id);
    }
}
