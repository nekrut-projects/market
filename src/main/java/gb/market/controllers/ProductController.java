package gb.market.controllers;

import gb.market.dto.ProductDto;
import gb.market.model.Category;
import gb.market.services.CategoryService;
import lombok.RequiredArgsConstructor;
import gb.market.exceptions.ResourceNotFoundException;
import gb.market.model.Product;
import gb.market.services.ProductService;
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
        Product p = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found, id: " + id));
        return new ProductDto(p);
    }

    @GetMapping
    public Page<ProductDto> getPage(@RequestParam(name = "p", defaultValue = "1") int pageNumber) {
        Page<ProductDto> page = productService.getPage(pageNumber - 1, 7).map(ProductDto::new);
        return page;
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable(name = "id") Long id) {
        productService.deleteById(id);
    }

    @PostMapping
    public ProductDto createNewProduct(@RequestBody ProductDto productDto) {
        Product product = new Product();
        product.setId(null);
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        Category category = categoryService.findByTitle(productDto.getCategory());
        product.setCategory(category);

        return new ProductDto(productService.save(product));
    }
}
