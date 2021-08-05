package gb.market.utils;

import gb.market.dto.ProductDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import gb.market.dto.OrderItemDto;
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

    public boolean isContainedProduct(Long productId){
        for (OrderItemDto oiDto : items){
            if (oiDto.getProductDto().getId().equals(productId)){
                return true;
            }
        }
        return false;
    }

    public void add(Long productId){
        for (OrderItemDto oiDto : items) {
            if (oiDto.getProductDto().getId().equals(productId)) {
                oiDto.changeQuantity(1);
            }
        }
        recalculate();
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

    public void delItem(Long id) {
        items.removeIf(oi -> oi.getProductDto().getId().equals(id));
        recalculate();
    }

    public void increaseQuantity(Long itemId) {
        findItemById(itemId).changeQuantity(1);
        recalculate();
    }

    public void decreaseQuantity(Long itemId) {
        OrderItemDto orderItemDto = findItemById(itemId);

        if (orderItemDto.getQuantity() > 1) {
            orderItemDto.changeQuantity(-1);
        } else {
            delItem(itemId);
        }
        recalculate();
    }

    private OrderItemDto findItemById(Long id) {
        OrderItemDto orderItemDto = null;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId().equals(id)){
                orderItemDto = items.get(i);
            }
        }
        return orderItemDto;
    }
}
