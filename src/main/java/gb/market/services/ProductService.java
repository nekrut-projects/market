package gb.market.services;

import gb.market.exceptions.InvalidInputDataException;
import gb.market.model.Product;
import gb.market.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductSpecificationsService productSpecService;

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public void deleteById(long id) {
        productRepository.deleteById(id);
    }

    public Product save(Product newProduct) {
        return productRepository.save(newProduct);
    }

    public Page<Product> showPage(int pageNumber, int size, Map<String, String> filters) throws InvalidInputDataException {
        return productRepository.findAll(productSpecService.addFilters(filters), PageRequest.of(pageNumber, size));
    }

    public List<Product> getAll(){
        return Collections.unmodifiableList(productRepository.findAll());
    }
}
