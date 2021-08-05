package gb.market.dto;

import gb.market.model.Order;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Data
public class OrderDto {
    private Long id;
    private BigDecimal price;
    private String address;
    private String phone;

    public OrderDto(Order order) {
        this.id = order.getId();
        this.price = order.getPrice();
        this.address = order.getAddress();
        this.phone = order.getPhone();
    }
}
