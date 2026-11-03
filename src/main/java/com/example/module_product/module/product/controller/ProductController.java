package com.example.module_product.module.product.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.example.module_product.common.dto.res.ApiResponse;
import com.example.module_product.common.dto.res.PageResponse;
import com.example.module_product.module.product.dto.req.ProductReqDto;
import com.example.module_product.module.product.dto.res.ProductResDto;
import com.example.module_product.module.product.service.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public PageResponse<ProductResDto> getAll(@ParameterObject @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return productService.list(pageable);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Void> create(@Valid @RequestBody ProductReqDto.Create createDto) {
        productService.createProduct(createDto);
        return ApiResponse.ok("Created", null);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<ProductResDto> getOne(@PathVariable Long id) {
        return ApiResponse.ok(productService.getOneProduct(id));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<Void> update(@PathVariable Long id, @Valid @RequestBody ProductReqDto.Update updateDto) {
        productService.updateProduct(id, updateDto);
        return ApiResponse.ok("Updated", null);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        productService.softDeleteProduct(id);
        return ApiResponse.ok("Deleted", null);
    }
    
}
