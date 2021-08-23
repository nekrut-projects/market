package gb.market.services;

import gb.market.exceptions.InvalidInputDataException;
import gb.market.model.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProductSpecificationsService {
    private final String FILTER_MIN_PRICE = "minPrice";
    private final String FILTER_MAX_PRICE = "maxPrice";
    private final String FILTER_TITLE_LIKE = "titleLike";

    private Specification<Product> spec;

    public Specification<Product> addFilters(Map<String, String> filters) throws InvalidInputDataException {
        if (filters == null){
            return null;
        }
        spec = Specification.where(null);
        List<String> errors = new ArrayList<>();
        if (filters.containsKey(FILTER_MIN_PRICE)){
            try {
                spec = spec.and(priceGreaterOrEqualsThan(BigDecimal.valueOf(Long.parseLong(filters.get(FILTER_MIN_PRICE)))));
            } catch (NumberFormatException e) {
                errors.add(String.format("Неверно указана минимальная цена: %s", filters.get(FILTER_MIN_PRICE)));
            }
        }
        if (filters.containsKey(FILTER_MAX_PRICE)){
            try {
                spec = spec.and(priceLessOrEqualsThan(BigDecimal.valueOf(Long.parseLong(filters.get(FILTER_MAX_PRICE)))));
            } catch (NumberFormatException e) {
                    errors.add(String.format("Неверно указана максимальная цена: %s", filters.get(FILTER_MAX_PRICE)));
            }
        }
        if (filters.containsKey(FILTER_TITLE_LIKE)){
            spec = spec.and(titleLike(filters.get(FILTER_TITLE_LIKE)));
        }

        if (!errors.isEmpty()){
            throw new InvalidInputDataException(errors);
        }
        return spec;
    }

    private Specification<Product> priceGreaterOrEqualsThan(BigDecimal minPrice){
        return ((root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice));
    }

    private Specification<Product> priceLessOrEqualsThan(BigDecimal maxPrice) {
        return ((root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice));
    }

    private Specification<Product> titleLike(String title){
        return ((root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get("title"), "%" + title + "%"));
    }
}
