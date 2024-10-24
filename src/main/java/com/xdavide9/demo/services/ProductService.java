package com.xdavide9.demo.services;

import com.xdavide9.demo.jpa.entities.product.Product;
import com.xdavide9.demo.jpa.repositories.product.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Page<Product> getProductsOfPage(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return productRepository.findAll(pageRequest);
    }

    public List<Product> getBestSellingProducts(int limit) {
        return productRepository.getBestSellingProducts(limit);
    }
}
