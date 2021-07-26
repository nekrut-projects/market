package market.market.services;

import lombok.RequiredArgsConstructor;
import market.market.model.OrderItem;
import market.market.model.Product;
import market.market.repositories.OrderItemRepository;
import market.market.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    public OrderItem save(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    public void save(List<OrderItem> orderItems) {
        for (OrderItem oi : orderItems){
            orderItemRepository.save(oi);
        }
    }
}
