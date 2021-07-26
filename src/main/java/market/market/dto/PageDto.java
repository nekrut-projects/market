package market.market.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import market.market.model.Product;
import org.springframework.data.domain.Page;

import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
public class PageDto<P> {
    private List<P> content;
    private int totalPages;
    private long totalElements;

    public PageDto(Page<Product> page) {
        content = new LinkedList<>();
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
        for (Product p : page.getContent()){
            content.add((P) new ProductDto(p));
        }
    }
}
