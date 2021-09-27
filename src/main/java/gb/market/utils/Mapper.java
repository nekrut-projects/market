package gb.market.utils;

import gb.market.dto.OrderDto;
import gb.market.dto.OrderItemDto;
import gb.market.dto.ProductDto;
import gb.market.model.Order;
import gb.market.model.OrderItem;
import gb.market.model.Product;

import java.util.List;
import java.util.stream.Collectors;

public class Mapper {
    public static ProductDto mappingDto(Product p){
        return new ProductDto(p.getId(), p.getTitle(), p.getPrice());
    }

    public static OrderItemDto mappingDto(OrderItem oi){
        return new OrderItemDto(oi.getId(), oi.getPrice(), oi.getQuantity(), mappingDto(oi.getProduct()));
    }

    public static OrderItemDto mappingDto(ProductDto p){
        return new OrderItemDto(p);
    }

    public static OrderDto mappingDto(Order o){
        return new OrderDto(o.getId(),
                            o.getPrice(),
                            o.getAddress(),
                            o.getPhone(),
                            o.getOrderItems().stream().map(Mapper::mappingDto).collect(Collectors.toList()));
    }

    public static List<OrderDto> mappingDto(List<Order> orders){
        return orders.stream().map(Mapper::mappingDto).collect(Collectors.toList());
    }

}
