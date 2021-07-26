package market.market.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import market.market.model.Product;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class OrderItemDto {
    private ProductDto productDto;
    private BigDecimal price;
    private int quantity;

    public OrderItemDto(ProductDto productDto) {
        this.productDto = productDto;
        this.price = productDto.getPrice();
        this.quantity = 1;
    }

    public void changeQuantity(int amount) {
        quantity += amount;
        price = productDto.getPrice().multiply(BigDecimal.valueOf(quantity));
    }
}
