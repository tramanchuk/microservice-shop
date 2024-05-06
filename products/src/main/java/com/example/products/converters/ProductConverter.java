package com.example.products.converters;

import com.example.products.model.Product;
import com.example.products.web.dto.ProductDto;
import java.util.List;

public interface ProductConverter {
    Product convert(ProductDto productDto);
    ProductDto convert(Product product);
    List<ProductDto> convert(List<Product> products);
}
