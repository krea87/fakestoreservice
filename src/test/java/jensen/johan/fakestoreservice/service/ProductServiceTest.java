package jensen.johan.fakestoreservice.service;

import jensen.johan.fakestoreservice.model.Product;
import jensen.johan.fakestoreservice.repository.ProductRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Test
    void getAllProducts() {
        ProductRepository repository = mock(ProductRepository.class);


        // Arrange
        Product product1 = new Product();
        product1.setTitle("test product 1");

        Product product2 = new Product();

        product2.setTitle("test product 2");

        List<Product> fakeProducts = List.of(product1, product2);

        when(repository.findAll()).thenReturn(fakeProducts);

        ProductService productService = new ProductService(repository);

        // Act
        List<Product> result = productService.getAllProducts();


        // Assert
        assertEquals(2, result.size());
        assertEquals("test product 1", result.get(0).getTitle());
        assertEquals("test product 2", result.get(1).getTitle());

        verify(repository).findAll();


    }
}