package com.example.products.converters.impl;

import com.example.products.converters.ProductConverter;
import com.example.products.model.Product;
import com.example.products.web.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductConverterImpl implements ProductConverter {
    @Override
    public Product convert(ProductDto productDto) {
        return new Product(productDto.getId(), productDto.getName(), productDto.getPrice());
    }

    @Override
    public ProductDto convert(Product product) {
        return new ProductDto(product.getId(), product.getName(), product.getPrice(), product.getCreatedDate(), product.getLastUpdateDate());
    }

    @Override
    public List<ProductDto> convert(List<Product> products) {
        return products.stream().map(this::convert)
                .collect(Collectors.toList());
    }
}
