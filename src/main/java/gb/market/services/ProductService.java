package gb.market.services;

import gb.market.exceptions.InvalidInputDataException;
import gb.market.model.Product;
import gb.market.repositories.ProductRepository;
import gb.market.repositories.specifications.ProductSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public void deleteById(long id) {
        productRepository.deleteById(id);
    }

    public Product save(Product newProduct) {
        return productRepository.save(newProduct);
    }

    public Page<Product> showPage(int pageNumber, int size, String filters) throws InvalidInputDataException {
        Specification<Product> spec = Specification.where(null);

        if (filters != null) {
            Map<String, String> mFilters = parseFilters(filters);
            spec = addFilters(spec, mFilters);
        }
        return productRepository.findAll(spec, PageRequest.of(pageNumber, size));
    }

    private Map<String, String> parseFilters(String filters) {
        Map<String, String> filtersMap = new HashMap<>();
        String[] str = filters.substring(1, filters.length() - 1).split(",");
        for (String s: str) {
            String[] arrS = s.replaceAll("\"","").split(":");
            if (arrS.length == 2) {
                filtersMap.put(arrS[0], arrS[1]);
            }
        }
        return filtersMap;
    }

    private Specification<Product> addFilters(Specification<Product> spec, Map<String, String> filters) throws InvalidInputDataException {
        List<String> errors = new ArrayList<>();
        for (String key : filters.keySet()) {
            switch (key){
                case "minPrice":
                    try {
                        spec = spec.and(ProductSpecifications.priceGreaterOrEqualsThan(
                                BigDecimal.valueOf(Long.parseLong(filters.get(key)))));
                    } catch (NumberFormatException e) {
                        errors.add(String.format("Неверно указана минимальная цена: %s", filters.get(key)));
                    }
                    break;
                case "maxPrice":
                    try {
                        spec = spec.and(ProductSpecifications.priceLessOrEqualsThan(
                                BigDecimal.valueOf(Long.parseLong(filters.get(key)))));
                    } catch (NumberFormatException e) {
                        errors.add(String.format("Неверно указана максимальная цена: %s", filters.get(key)));
                    }
                    break;
                case "title":
                    spec = spec.and(ProductSpecifications.titleLike(filters.get(key)));
                    break;
            }
        }
        if (!errors.isEmpty()){
            throw new InvalidInputDataException(errors);
        }
        return spec;
    }
}
