package ua.citizen.productservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.citizen.productservice.model.Product;
import ua.citizen.productservice.service.ProductService;
import java.util.Collections;
import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService service;
    private final ObjectMapper mapper;

    @PostMapping
    public Product add(@RequestBody Product product) {
        return service.add(product);
    }

    @PutMapping
    public Product update(@RequestBody Product product) {
        return service.update(product);
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable("id") Long id) throws Exception {
        return service.findById(id);
    }

    @PostMapping("/ids")
    public List<Product> find(@RequestBody List<Long> ids) throws JsonProcessingException {
        var products = service.find(ids);
        log.info("Products found: {}", mapper.writeValueAsString(Collections.singletonMap("count", products.size())));
        return products;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }
}