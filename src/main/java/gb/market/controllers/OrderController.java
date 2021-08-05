package gb.market.controllers;

import gb.market.dto.OrderDto;
import gb.market.model.User;
import gb.market.services.OrderService;
import gb.market.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;

    @PostMapping("/add")
    public void createOrder(Principal principal) {
        User user = userService.findByName(principal.getName()).get();
        orderService.createOrder(user);
    }

    @GetMapping
    public List<OrderDto> getAllOrders() {
        return orderService.findAll().stream().map(OrderDto::new).collect(Collectors.toList());
    }

    @GetMapping("/user")
    public List<OrderDto> getUserOrders(Principal principal) {
        User user = userService.findByName(principal.getName()).get();
        return orderService.findByUser(user).stream().map(OrderDto::new).collect(Collectors.toList());
    }
}
