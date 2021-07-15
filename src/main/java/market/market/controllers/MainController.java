package market.market.controllers;

import lombok.RequiredArgsConstructor;
import market.market.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final ProductService productService;

    @GetMapping
    public String showMainPage(Model model){
        model.addAttribute("products", productService.findAllProducts());
        return "index";
    }
}
