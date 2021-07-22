package market.market.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import market.market.model.Category;

@Data
@NoArgsConstructor
public class CategoryDto {
    private Long id;
    private String title;

    public CategoryDto(Category category) {
        this.id = category.getId();
        this.title = category.getTitle();
    }
}
