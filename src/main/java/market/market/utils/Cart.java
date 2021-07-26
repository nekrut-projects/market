package market.market.utils;

import lombok.Data;
import lombok.NoArgsConstructor;
import market.market.dto.OrderItemDto;
import market.market.dto.ProductDto;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@NoArgsConstructor
@Data
public class Cart {
    private List<OrderItemDto> items;
    private BigDecimal price;

    @PostConstruct
    public void init() {
        this.items = new ArrayList<>();
        this.price = BigDecimal.ZERO;
    }

    public void clear() {
        items.clear();
        price = BigDecimal.ZERO;
    }

    public boolean add(Long productId) {
        for (OrderItemDto o : items) {
            if (o.getProductDto().getId().equals(productId)) {
                o.changeQuantity(1);
                recalculate();
                return true;
            }
        }
        return false;
    }

    public void add(ProductDto product) {
        items.add(new OrderItemDto(product));
        recalculate();
    }

    private void recalculate() {
        price = BigDecimal.ZERO;
        for (OrderItemDto oid : items) {
            price = price.add(oid.getPrice());
        }
    }
}
