package com.xdavide9.demo.properties.home;

import com.xdavide9.demo.properties.HomeProperties;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class HomePropertiesTest {

    @Autowired
    private HomeProperties underTest;

    @Test
    void itShouldGetProductsPerPage() {
        AssertionsForClassTypes.assertThat(underTest.getProductsPerPage()).isEqualTo(10);
    }

    @Test
    void itShouldGetNumberOfBestSellingProducts() {
        AssertionsForClassTypes.assertThat(underTest.getNumberOfBestSellingProducts()).isEqualTo(5);
    }
}