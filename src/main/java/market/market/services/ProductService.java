package market.market.services;

import lombok.RequiredArgsConstructor;
import market.market.model.Product;
import market.market.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.getById(id);
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public void deleteProductById(long id) {
        productRepository.deleteById(id);
    }

    public Page<Product> getPage(int pageNumber, int size) {
        return productRepository.findAll(PageRequest.of(pageNumber, size));
    }
}
