package com.xdavide9.demo.web.cart;

import com.xdavide9.demo.jpa.entities.product.cart.CartProduct;
import com.xdavide9.demo.jpa.repositories.product.cart.CartProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CartService {

    private final CartProductRepository cartProductRepository;

    public List<CartProduct> getCartProducts() {
        UserDetails principal = (UserDetails) authentication().getPrincipal();
        return cartProductRepository.findAllByUser(principal.getUsername());
    }

    private Authentication authentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
