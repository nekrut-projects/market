package market.market.services;

import lombok.RequiredArgsConstructor;
import market.market.model.Product;
import market.market.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public Product findProductById(Long id) {
        return productRepository.getById(id);
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public void deleteProductById(long id) {
        productRepository.deleteById(id);
    }

    public List<Product> findByPriceGreaterThan(int minPrice) {
        return productRepository.findByPriceGreaterThan(minPrice);
    }

    public List<Product> findByPriceLessThan(int maxPrice) {
        return productRepository.findByPriceLessThan(maxPrice);
    }
    public List<Product> findByPriceBetween(int minPrice, int maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }
}
