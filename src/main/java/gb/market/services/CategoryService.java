package gb.market.services;

import lombok.RequiredArgsConstructor;
import gb.market.model.Category;
import gb.market.repositories.CategoryRepository;
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
