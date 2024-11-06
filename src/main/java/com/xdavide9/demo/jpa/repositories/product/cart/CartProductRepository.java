package com.xdavide9.demo.jpa.repositories.product.cart;

import com.xdavide9.demo.jpa.entities.product.cart.CartProduct;
import com.xdavide9.demo.jpa.entities.product.cart.CartProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartProductRepository extends JpaRepository<CartProduct, CartProductId> {
    @Query("SELECT cp FROM CartProduct cp WHERE cp.user.email = :email")
    List<CartProduct> findAllByUser(String email);
}
