package com.xdavide9.demo.web.home;

import com.xdavide9.demo.jpa.entities.product.Product;
import com.xdavide9.demo.jpa.repositories.product.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    private HomeService underTest;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        underTest = new HomeService(productRepository);
    }

    @Test
    void itShouldGetProductsOfPage() {
        // given
        int pageNumber = 0;
        int pageSize = 10;
        Product product = new Product();
        List<Product> products = List.of(product);
        when(productRepository.findAll(PageRequest.of(pageNumber, pageSize))).thenReturn(new PageImpl<>(products));
        // when
        Page<Product> productsOfPage = underTest.getProductsOfPage(pageNumber, pageSize);
        // then
        assertThat(productsOfPage.getContent().contains(product)).isTrue();
    }

    @Test
    void itShouldGetBestSellingProducts() {
        // given
        int limit = 5;
        when(productRepository.getBestSellingProducts(limit)).thenReturn(List.of(
                new Product(),
                new Product(),
                new Product(),
                new Product(),
                new Product()
        ));
        // when
        List<Product> bestSellingProducts = underTest.getBestSellingProducts(limit);
        // then
        assertThat(bestSellingProducts.size()).isEqualTo(5);
    }
}