package com.xdavide9.demo.jpa.repositories.product;

import com.xdavide9.demo.jpa.entities.product.Product;
import jakarta.transaction.Transactional;
import org.assertj.core.api.AssertionsForInterfaceTypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
class ProductRepositoryTest {

    @Autowired
    private ProductRepository underTest;

    @Test
    void itShouldGetBestSellingProducts() {
        // given
        int limit = 2;
        Product product1 = new Product(1L, "p1", "c1", 1, 10, "d1", BigDecimal.valueOf(10));
        Product product2 = new Product(2L, "p2", "c2", 1, 100, "d2", BigDecimal.valueOf(10));
        Product product3 = new Product(3L, "p3", "c3", 1, 200, "d3", BigDecimal.valueOf(10));
        Product product5 = new Product(4L, "p4", "c4", 1, 300, "d4", BigDecimal.valueOf(10));
        Product product4 = new Product(5L, "p5", "c5", 1, 500, "d5", BigDecimal.valueOf(10));
        underTest.saveAll(List.of(product1, product2, product3, product4, product5));
        // when
        List<Product> bestSellingProducts = underTest.getBestSellingProducts(limit);
        // then
        AssertionsForInterfaceTypes.assertThat(bestSellingProducts).hasSize(2);
    }
}