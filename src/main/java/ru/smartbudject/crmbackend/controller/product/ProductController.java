package ru.smartbudject.crmbackend.controller.product;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.smartbudject.crmbackend.model.dto.product.AddProductRequest;
import ru.smartbudject.crmbackend.service.ProductService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @PostMapping()
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Long> addProduct(@RequestBody AddProductRequest addProductRequest) {
        return ResponseEntity.ok(productService.addProduct(addProductRequest));
    }

}
