package gb.market.controllers;

import gb.market.dto.StringResponse;
import gb.market.services.CartService;
import gb.market.utils.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.security.Principal;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping("/{uuid}")
    public Cart getCart(Principal principal, @PathVariable String uuid) {
        return cartService.getCurrentCart(getCurrentCartUuid(principal, uuid));
    }

    @GetMapping("/generate")
    public StringResponse getCart() {
        return new StringResponse(cartService.generateCartUuid());
    }

    @GetMapping("/{uuid}/add/{id}")
    public void add(Principal principal, @PathVariable String uuid, @PathVariable Long id) {
        cartService.addToCart(getCurrentCartUuid(principal, uuid), id);
    }

    @GetMapping("{uuid}/decrease/{id}")
    public void decreaseQuantity(Principal principal, @PathVariable String uuid, @PathVariable Long id) {
        cartService.decreaseQuantity(getCurrentCartUuid(principal, uuid), id);
    }

    @GetMapping("{uuid}/increase/{id}")
    public void increaseQuantity(Principal principal, @PathVariable String uuid, @PathVariable Long id) {
        cartService.increaseQuantity(getCurrentCartUuid(principal, uuid), id);
    }

    @GetMapping("/{uuid}/del/{id}")
    public void delItemFromCart(Principal principal, @PathVariable String uuid, @PathVariable Long id) {
        cartService.delItemFromCart(getCurrentCartUuid(principal, uuid), id);
    }

    @GetMapping("/{uuid}/clear")
    public void clearCart(Principal principal, @PathVariable String uuid) {
        cartService.clearCart(getCurrentCartUuid(principal, uuid));
    }

    @GetMapping("/{uuid}/merge")
    public void merge(Principal principal, @PathVariable String uuid) {
        cartService.merge(
                getCurrentCartUuid(principal, null),
                getCurrentCartUuid(null, uuid)
        );
    }

    private String getCurrentCartUuid(Principal principal, String uuid) {
        if (principal != null) {
            return cartService.getCartUuidFromSuffix(principal.getName());
        }
        return cartService.getCartUuidFromSuffix(uuid);
    }


}