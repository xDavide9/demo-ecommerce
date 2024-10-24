package com.xdavide9.demo.controllers.home;

import com.xdavide9.demo.jpa.entities.product.Product;
import com.xdavide9.demo.properties.HomeProperties;
import com.xdavide9.demo.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class HomeController {

    private final ProductService productService;
    private final HomeProperties homeProperties;

    @GetMapping("/home")
    public String home(@RequestParam(defaultValue = "1", name = "page") int page, Model model) {
        int pageSize = homeProperties.getProductsPerPage();
        int numberOfBestSellingProducts = homeProperties.getNumberOfBestSellingProducts();
        Page<Product> productPage = productService.getProductsOfPage(page - 1, pageSize);
        model.addAttribute("products", productPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("bestSellingProducts", productService.getBestSellingProducts(numberOfBestSellingProducts));
        return "home";
    }
}
