package com.xdavide9.demo.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("app.home")
@Getter
@Setter
public class HomeProperties {
    private int productsPerPage;
    private int numberOfBestSellingProducts;
}
