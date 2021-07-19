package market.market.controllers;

import lombok.RequiredArgsConstructor;
import market.market.model.Product;
import market.market.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/products/{id}")
    public Product findById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @GetMapping("/products")
    public Page<Product> getPage(@RequestParam(name = "p") int pageNumber) {
        return productService.getPage(pageNumber - 1, 7);
    }

    @GetMapping("/products/del")
    public void deleteProduct(@RequestParam(name = "id") Long id) {
        productService.deleteProductById(id);
    }

//    public List<Product> findAll() {
//    @GetMapping("/products")
//        return productService.findAllProducts();
//    }

}
