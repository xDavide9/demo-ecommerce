package com.xdavide9.demo.controllers.home;

import com.xdavide9.demo.jpa.entities.product.Product;
import com.xdavide9.demo.properties.HomeProperties;
import com.xdavide9.demo.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HomeController.class)
class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductService productService;
    @MockBean
    private HomeProperties homeProperties;

    private final int productsPerPage = 10;
    private final int numberOfBestSellingProducts = 5;
    private final int totalProducts = 100;
    private final int totalPages = totalProducts / productsPerPage;

    @BeforeEach
    void setUp() {
        when(homeProperties.getProductsPerPage()).thenReturn(productsPerPage);
        when(homeProperties.getNumberOfBestSellingProducts()).thenReturn(numberOfBestSellingProducts);
    }

    @Test
    void itShouldReturnHomeDefaultPage() throws Exception {
        List<Product> products = new ArrayList<>();
        Page<Product> page = new PageImpl<>(products, PageRequest.of(0, productsPerPage), totalProducts);
        when(productService.getProductsOfPage(0, productsPerPage)).thenReturn(page);
        when(productService.getBestSellingProducts(numberOfBestSellingProducts)).thenReturn(products);
        mockMvc.perform(MockMvcRequestBuilders.get("/home"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("currentPage", 1))
                .andExpect(model().attribute("totalPages", totalPages))
                .andExpect(model().attributeExists("products"))
                .andExpect(model().attributeExists("bestSellingProducts"));
    }

    @Test
    void itShouldReturnHomeSomePage() throws Exception {
        List<Product> products = new ArrayList<>();
        Page<Product> page = new PageImpl<>(products, PageRequest.of(2, productsPerPage), totalProducts);
        when(productService.getProductsOfPage(2, productsPerPage)).thenReturn(page);
        when(productService.getBestSellingProducts(numberOfBestSellingProducts)).thenReturn(products);
        mockMvc.perform(MockMvcRequestBuilders.get("/home?page=3"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("currentPage", 3))
                .andExpect(model().attribute("totalPages", totalPages))
                .andExpect(model().attributeExists("products"))
                .andExpect(model().attributeExists("bestSellingProducts"));
    }
}