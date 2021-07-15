package market.market.repositories;

import market.market.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByPriceGreaterThan(int minPrice);
    List<Product> findByPriceLessThan(int maxPrice);
    List<Product> findByPriceBetween(int minPrice, int maxPrice);

}
