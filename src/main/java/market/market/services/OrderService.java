package market.market.services;

import lombok.RequiredArgsConstructor;
import market.market.model.Order;
import market.market.model.OrderItem;
import market.market.model.Product;
import market.market.repositories.OrderRepository;
import market.market.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public Order save(Order orderItem) {
        return orderRepository.save(orderItem);
    }
}
