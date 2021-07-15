package market.market.controllers;

import lombok.RequiredArgsConstructor;
import market.market.model.Product;
import market.market.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping(value = "/add_form")
    public String getAddForm(){
        return "add_form";
    }

    @PostMapping(value = "/add")
    public String addProduct(@RequestParam String title, @RequestParam int price){
        productService.addProduct(new Product(title, price));
        return "redirect:/add_form";
    }

    @GetMapping(value = "/del_product/{id}")
    public String delProduct(@PathVariable Long id){
        productService.deleteProductById(id);
        return "redirect:/";
    }

    @GetMapping(value = "/products/price_greater")
    @ResponseBody
    public List<Product> findByPriceGreaterThan(@RequestParam(name = "min") int min) {
        return productService.findByPriceGreaterThan(min);
    }

    @GetMapping(value = "/products/price_less")
    @ResponseBody
    public List<Product> findByPriceLessThan(@RequestParam(name = "max") int max) {
        return productService.findByPriceLessThan(max);
    }

    @GetMapping(value = "/products/price_between")
    @ResponseBody
    public List<Product> findByPriceGreaterThan(@RequestParam(name = "min") int min,
                                                @RequestParam(name = "max") int max) {
        return productService.findByPriceBetween(min, max);
    }
}
