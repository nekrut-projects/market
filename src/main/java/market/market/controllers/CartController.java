package market.market.controllers;

import lombok.RequiredArgsConstructor;
import market.market.dto.OrderItemDto;
import market.market.dto.ProductDto;
import market.market.exceptions.ResourceNotFoundException;
import market.market.model.Order;
import market.market.model.OrderItem;
import market.market.model.Product;
import market.market.services.OrderItemService;
import market.market.services.OrderService;
import market.market.services.ProductService;
import market.market.utils.Cart;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final Cart cart;
    private final ProductService productService;
    private final OrderService orderService;
    private final OrderItemService orderItemService;

    @GetMapping
    public Cart getCart() {
        return cart;
    }

    @GetMapping("/add/{productId}")
    public void add(@PathVariable Long productId) {
        if (!cart.add(productId)) {
            cart.add(new ProductDto(productService.findById(productId)
                    .orElseThrow(() -> new ResourceNotFoundException("Unable add product to cart. Product not found id: " + productId))));
        }
    }

    @GetMapping("/order")
    public void placeAnOrder(){
        Order order = new Order(2L);
        orderService.save(order);
        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemDto oiDto : cart.getItems()){
            OrderItem orderItem = new OrderItem();
            orderItem.setPrice(oiDto.getPrice());
            orderItem.setQuantity(oiDto.getQuantity());
            Product p = productService.findById(oiDto.getProductDto().getId()).get();
            orderItem.setProduct(p);
            orderItem.setOrder(order);
            orderItems.add(orderItem);
        }
        order.setOrderItems(orderItems);
        orderService.save(order);
        orderItemService.save(orderItems);
    }
}
