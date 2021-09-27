package gb.market.dto;

import gb.market.model.Order;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Data
public class OrderDto {
    private Long id;
    private BigDecimal price;
    private String address;
    private String phone;
    private List<OrderItemDto> orderItems;

//    public OrderDto(Order order) {
//        this.id = order.getId();
//        this.price = order.getPrice();
//        this.address = order.getAddress();
//        this.phone = order.getPhone();
//        this.orderItems = order.getOrderItems().stream().map(OrderItemDto::new).collect(Collectors.toList());
//    }

    public OrderDto(Long id, BigDecimal price, String address,String phone, List<OrderItemDto> orderItems) {
        this.id = id;
        this.price = price;
        this.address = address;
        this.phone = phone;
        this.orderItems = orderItems;
    }
}
