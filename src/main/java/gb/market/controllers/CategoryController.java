package gb.market.controllers;

import gb.market.services.CategoryService;
import lombok.RequiredArgsConstructor;
import gb.market.dto.CategoryDto;
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
