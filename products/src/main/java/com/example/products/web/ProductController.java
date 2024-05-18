package com.example.products.web;

import com.example.products.config.logs.Loggable;
import com.example.products.converters.ProductConverter;
import com.example.products.facades.ProductFacade;
import com.example.products.model.Product;
import com.example.products.web.dto.ProductDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
import io.vavr.control.Try;

@RestController
@RequestMapping("/v1/products")
public class ProductController {

    private final ProductFacade productFacade;
    private final ProductConverter productConverter;

    public ProductController(ProductFacade productFacade, ProductConverter productConverter) {
        this.productFacade = productFacade;
        this.productConverter = productConverter;
    }

    @Loggable
    @GetMapping
    public List<ProductDto> getAllProducts(){
        List<Product> products = this.productFacade.getProducts();
        return this.productConverter.convert(products);
    }
    @Loggable
    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable UUID id){
        if (UUID.fromString("7b84df18-3483-4ef4-a06e-08d8a4e31a5b").equals(id)){
            Try.run(() -> Thread.sleep(5000));
        }
        Product product = this.productFacade.getProductById(id);
        return this.productConverter.convert(product);
    }

    @Loggable
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto createProduct(@RequestBody ProductDto productDto){
        Product product = this.productConverter.convert(productDto);
        this.productFacade.save(product);
        return this.productConverter.convert(product);
    }

    @GetMapping("/failure")
    public List<ProductDto> getProductstFailure(){
        Try.run(() -> Thread.sleep(5000));
        return getAllProducts();
    }
}
