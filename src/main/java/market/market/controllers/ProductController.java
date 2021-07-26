package market.market.controllers;

import lombok.RequiredArgsConstructor;
import market.market.dto.PageDto;
import market.market.dto.ProductDto;
import market.market.model.Category;
import market.market.model.Product;
import market.market.services.CategoryService;
import market.market.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable Long id) {
        return new ProductDto(productService.findById(id));
    }

    @GetMapping
    public PageDto<ProductDto> getPage(@RequestParam(name = "p", defaultValue = "1") int pageNumber) {
        Page<Product> page = productService.getPage(pageNumber - 1, 7);
        return new PageDto<>(page);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable(name = "id") Long id) {
        productService.deleteById(id);
    }

    @PostMapping
    public Product createNewProduct(@RequestBody ProductDto productDto) {
        Product product = new Product();
        product.setId(null);
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        Category category = categoryService.findByTitle(productDto.getCategory());
        product.setCategory(category);

        return productService.save(product);
    }
}
