package com.example.products.web;

import com.example.products.config.logs.Loggable;
import com.example.products.converters.ProductConverter;
import com.example.products.facades.ProductFacade;
import com.example.products.model.Product;
import com.example.products.web.dto.ProductDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
import io.vavr.control.Try;

@Tag(name = "Products", description = "Product management APIs")
@RestController
@RequestMapping("/v1/products")
public class ProductController {

    private final ProductFacade productFacade;
    private final ProductConverter productConverter;

    public ProductController(ProductFacade productFacade, ProductConverter productConverter) {
        this.productFacade = productFacade;
        this.productConverter = productConverter;
    }

    @Operation(
            summary = "Retrieve all products",
            description = "Retrieve list of products without sorting, filters, pagination",
            tags = { "Products"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = List.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @Loggable
    @GetMapping
    public List<ProductDto> getAllProducts(){
        List<Product> products = this.productFacade.getProducts();
        return this.productConverter.convert(products);
    }
    @Operation(
            summary = "Retrieve Product by Id",
            tags = { "Products"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = ProductDto.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })

    @Loggable
    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable UUID id){
        if (UUID.fromString("7b84df18-3483-4ef4-a06e-08d8a4e31a5b").equals(id)){
            Try.run(() -> Thread.sleep(5000));
        }
        Product product = this.productFacade.getProductById(id);
        return this.productConverter.convert(product);
    }
    //@PreAuthorize("hasRole('ADMIN')")
    @Operation(
            summary = "Create a Product",
            tags = { "Products"})
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = ProductDto.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @Loggable
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto createProduct(@RequestBody ProductDto productDto){
        Product product = this.productConverter.convert(productDto);
        this.productFacade.save(product);
        return this.productConverter.convert(product);
    }
    @Operation(
            summary = "Example of getting all products with delay",
            description = "Return all products in 5 seconds",
            tags = { "Products"})
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = List.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })

    @GetMapping("/failure")
    public List<ProductDto> getAllProductsWithDelay(){
        Try.run(() -> Thread.sleep(5000));
        return getAllProducts();
    }

    @GetMapping("/me")
    public Object getMe() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
