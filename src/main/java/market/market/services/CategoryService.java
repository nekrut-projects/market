package market.market.services;

import lombok.RequiredArgsConstructor;
import market.market.model.Category;
import market.market.repositories.CategoryRepository;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category findById(Long id){
        return categoryRepository.findById(id).get();
    }

    public Category findByTitle(String title) {
        return categoryRepository.findByTitle(title);
    }
}
