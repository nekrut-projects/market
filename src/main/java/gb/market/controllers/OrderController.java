package gb.market.controllers;

import gb.market.dto.OrderDto;
import gb.market.dto.OrderItemDto;
import gb.market.exceptions.InvalidInputDataException;
import gb.market.model.User;
import gb.market.services.OrderService;
import gb.market.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;

    @PostMapping("/orders/add")
    public void createOrder(Principal principal, @RequestParam String address, @RequestParam String phone) {
        List<String> errors = new ArrayList<>();
        checkAddress(address, errors);
        checkPhone(phone, errors);
        if (!errors.isEmpty()){
            throw new InvalidInputDataException(errors);
        }
        User user = userService.findByName(principal.getName()).get();
        orderService.createOrder(user, address, phone);
    }

    @GetMapping("/orders")
    public List<OrderDto> getUserOrders(Principal principal) {
        return orderService.findByUsername(principal.getName()).stream().map(OrderDto::new).collect(Collectors.toList());
    }

    @GetMapping("/order/{id}")
    public List<OrderItemDto>showInfoOrder(@PathVariable("id") Long id){
        return orderService.getItemsOrder(id).stream().map(oi -> new OrderItemDto(oi)).collect(Collectors.toList());
    }

    private void checkAddress(String address, List<String> errors){
        if (address == null || address.trim().isEmpty()){
            errors.add(String.format("Неверно указан адрес доставки: \"%s\"", address));
        }
    }

    private void checkPhone(String phone, List<String> errors){
        if (phone == null || phone.trim().isEmpty() || phone.length() != 11){
            errors.add(String.format("Неверно указан телефон: \"%s\"", phone));
        }
    }
}
