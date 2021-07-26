package market.market.services;

import lombok.RequiredArgsConstructor;
import market.market.model.Product;
import market.market.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Page<Product> getPage(int pageNumber, int size) {
        return productRepository.findAll(PageRequest.of(pageNumber, size));
    }
}
