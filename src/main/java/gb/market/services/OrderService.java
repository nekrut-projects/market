package gb.market.services;

import gb.market.dto.OrderItemDto;
import gb.market.exceptions.ResourceNotFoundException;
import gb.market.model.Order;
import gb.market.model.OrderItem;
import gb.market.model.Product;
import gb.market.model.User;
import gb.market.repositories.OrderRepository;
import gb.market.utils.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final CartService cartService;

    @Transactional
    public void createOrder(User user, String address, String phone) {
        Cart cart = cartService.getCurrentCart(cartService.getCartUuidFromSuffix(user.getName()));
        Order order = new Order();
        order.setPrice(cart.getPrice());
        order.setOrderItems(new ArrayList<>());
        order.setUser(user);
        order.setAddress(address);
        order.setPhone(phone);
        for (OrderItemDto o : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setQuantity(o.getQuantity());
            Product product = productService.findById(o.getProductDto().getId()).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
            orderItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(o.getQuantity())));
            orderItem.setProduct(product);
            order.getOrderItems().add(orderItem);
        }
        orderRepository.save(order);
        cart.clear();
        cartService.updateCart(cartService.getCartUuidFromSuffix(user.getName()), cart);
    }

    public List<Order> findByUser(User user) {
        return orderRepository.findByUser(user);
    }

    public List<OrderItem> getItemsOrder(Long orderId){
        Order order = orderRepository.getById(orderId);
        return order.getOrderItems();
    }

    public List<Order> findByUsername(String username) {
        return orderRepository.findByUsername(username);
    }
}
