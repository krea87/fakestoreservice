package jensen.johan.fakestoreservice.service;

import jensen.johan.fakestoreservice.model.Product;
import jensen.johan.fakestoreservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {



    private final ProductRepository repository;
    private final RestTemplate restTemplate;


    public ProductService(ProductRepository repository) {
        this.repository = repository;
        this.restTemplate = new RestTemplate();
    }

    public List<Product> fetchAndSaveProducts() {
        //adding this since environment variable is not working properly in AWS

        String url = "https://fakestoreapi.com/products";
        Product[] response = restTemplate.getForObject(
                url,
                Product[].class
        );

        List <Product> products = Arrays.asList(response);

        repository.saveAll(products);

        return repository.findAll();
    }

    public List<Product> getAllProducts() {
        List<Product> products = repository.findAll();
        if (products.isEmpty()) {
            return fetchAndSaveProducts();
        }
        return products;
    }

}
