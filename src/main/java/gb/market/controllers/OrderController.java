package gb.market.controllers;

import gb.market.dto.OrderDto;
import gb.market.dto.OrderItemDto;
import gb.market.exceptions.IncorrectUserDataException;
import gb.market.model.User;
import gb.market.services.OrderService;
import gb.market.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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
        checkAddress(address);
        checkPhone(phone);
        User user = userService.findByName(principal.getName()).get();
        orderService.createOrder(user, address, phone);
    }

    @GetMapping
    public List<OrderDto> getAllOrders() {
        return orderService.findAll().stream().map(OrderDto::new).collect(Collectors.toList());
    }

    @GetMapping("/orders/user")
    public List<OrderDto> getUserOrders(Principal principal) {
        User user = userService.findByName(principal.getName()).get();
        return orderService.findByUser(user).stream().map(OrderDto::new).collect(Collectors.toList());
    }

    @GetMapping("/order/{id}")
    public List<OrderItemDto>showInfoOrder(@PathVariable("id") Long id){
        return orderService.getItemsOrder(id).stream().map(oi -> new OrderItemDto(oi)).collect(Collectors.toList());
    }

    private void checkAddress(String address){
        if (address.trim().equals("")){
            throw new IncorrectUserDataException(String.format("Неверно указан адрес доставки: \"%s\"", address));
        }
    }

    private void checkPhone(String phone){
        String temp = phone.trim();
        if (temp.equals("") || temp.length() != 11){
            throw new IncorrectUserDataException(String.format("Неверно указан телефон: \"%s\"", phone));
        }
    }
}
