package jensen.johan.fakestoreservice.service;

import jensen.johan.fakestoreservice.model.Product;
import jensen.johan.fakestoreservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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

        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<>(headers);


        ResponseEntity<Product[]> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                Product[].class
        );

        List <Product> products = Arrays.asList(response.getBody());
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
