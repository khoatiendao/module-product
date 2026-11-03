package com.example.module_product.module.product.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.module_product.common.constant.MessageConstant;
import com.example.module_product.common.dto.res.PageResponse;
import com.example.module_product.common.exception.AppException;
import com.example.module_product.common.exception.ErrorCode;
import com.example.module_product.module.product.dto.req.ProductReqDto;
import com.example.module_product.module.product.dto.res.ProductResDto;
import com.example.module_product.module.product.entity.Product;
import com.example.module_product.module.product.repository.ProductRepository;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public PageResponse<ProductResDto> list(Pageable pageable) {
        return PageResponse.from(productRepository.findByActiveTrue(pageable), ProductResDto::from);
    }

    public void createProduct(ProductReqDto.Create createDto) {
        Product product = new Product();
        product.setName(createDto.name());
        product.setPrice(createDto.price());
        product.setQuantity(createDto.quantity());
        product.setCategory(createDto.category());
        productRepository.save(product);
    }

    public ProductResDto getOneProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND, MessageConstant.PRODUCT_NOT_FOUND));
        
        if(product.getActive() == false) {
            throw new AppException(ErrorCode.NOT_FOUND, MessageConstant.PRODUCT_NOT_FOUND);
        }

        return ProductResDto.from(product);
    }

    public void updateProduct(Long id, ProductReqDto.Update updateDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND, MessageConstant.PRODUCT_NOT_FOUND));
        
        product.setName(updateDto.name());
        product.setPrice(updateDto.price());
        product.setQuantity(updateDto.quantity());
        product.setCategory(updateDto.category());
        productRepository.save(product);
    }

    public void softDeleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND, MessageConstant.PRODUCT_NOT_FOUND));

        product.setActive(false);
        productRepository.save(product);
    }
}
