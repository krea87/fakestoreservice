package jensen.johan.fakestoreservice.controller;

import jensen.johan.fakestoreservice.model.Product;
import jensen.johan.fakestoreservice.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping("/fetch")
    public List<Product> fetchProduct() {
        return service.fetchAndSaveProducts();
    }


    // Get all products
    @GetMapping
    public List<Product> getAll() {
        return service.getAllProducts();
    }
}
