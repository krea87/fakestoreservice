package jensen.johan.fakestoreservice.repository;

import jensen.johan.fakestoreservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
