package gb.market.dto;

import gb.market.model.OrderItem;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class OrderItemDto {
    private Long id;
    private ProductDto productDto;
    private BigDecimal price;
    private int quantity;

    public OrderItemDto(ProductDto productDto) {
        this.id = productDto.getId();
        this.productDto = productDto;
        this.price = productDto.getPrice();
        this.quantity = 1;
    }

//    public OrderItemDto(OrderItem orderItem) {
//        this.id = orderItem.getId();
//        this.productDto = new ProductDto(orderItem.getProduct());
//        this.price = orderItem.getPrice();
//        this.quantity = orderItem.getQuantity();
//    }

    public OrderItemDto(Long id, BigDecimal price, int quantity, ProductDto productDto) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.productDto = productDto;
    }

    public void changeQuantity(int amount) {
        quantity += amount;
        price = productDto.getPrice().multiply(BigDecimal.valueOf(quantity));
    }
}
