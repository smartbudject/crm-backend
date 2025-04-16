package ru.smartbudject.crmbackend.service.product;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.smartbudject.crmbackend.mapper.product.ProductMapper;
import ru.smartbudject.crmbackend.model.dto.product.AddProductRequest;
import ru.smartbudject.crmbackend.model.entity.Product;
import ru.smartbudject.crmbackend.repository.AccountRepository;
import ru.smartbudject.crmbackend.repository.PointSalesRepository;
import ru.smartbudject.crmbackend.repository.ProductRepository;
import ru.smartbudject.crmbackend.service.PointSalesService;
import ru.smartbudject.crmbackend.service.ProductService;
import ru.smartbudject.crmbackend.service.UserService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final UserService userService;
    private final ProductMapper productMapper;
    private final PointSalesRepository pointSalesRepository;


    @Override
    @Transactional
    public Long addProduct(final AddProductRequest addProductRequest) {
        final Product product = productMapper.mapAddProduct(addProductRequest);
        product.setAccount(userService.tryGetAuthenticated()
                .orElseThrow(EntityNotFoundException::new));
        product.setPointSales(pointSalesRepository.findById(product.getPointSales().getId()).orElseThrow(EntityNotFoundException::new));
        return productRepository.save(product)
                .getId();
    }

}
