package gb.market.services;

import lombok.RequiredArgsConstructor;
import gb.market.model.OrderItem;
import gb.market.repositories.OrderItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
