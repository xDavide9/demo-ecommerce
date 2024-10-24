package com.xdavide9.demo.jpa.repositories.product;

import com.xdavide9.demo.jpa.entities.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p ORDER BY p.quantitySold DESC LIMIT ?1")
    List<Product> getBestSellingProducts(int limit);
}
