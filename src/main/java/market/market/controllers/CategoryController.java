package market.market.controllers;

import lombok.RequiredArgsConstructor;
import market.market.dto.CategoryDto;
import market.market.model.Category;
import market.market.model.Product;
import market.market.services.CategoryService;
import market.market.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/{id}")
    public CategoryDto findById(@PathVariable Long id) {
        return new CategoryDto(categoryService.findById(id));
    }

}
