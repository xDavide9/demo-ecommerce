package com.xdavide9.demo.web.cart;

import com.xdavide9.demo.jpa.entities.product.cart.CartProduct;
import com.xdavide9.demo.jpa.entities.user.User;
import com.xdavide9.demo.jpa.repositories.product.cart.CartProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {

    @Mock
    private CartProductRepository cartProductRepository;
    @Mock
    private SecurityContext securityContext;
    @Mock
    private Authentication authentication;
    private CartService underTest;

    @BeforeEach
    void setUp() {
        SecurityContextHolder.setContext(securityContext);
        underTest = new CartService(cartProductRepository);
    }

    @Test
    void itShouldGetCartProducts() {
        // given
        User user = new User();
        given(securityContext.getAuthentication()).willReturn(authentication);
        given(authentication.getPrincipal()).willReturn(user);
        List<CartProduct> expected = new ArrayList<>();
        given(cartProductRepository.findAllByUser(user)).willReturn(expected);
        // when
        List<CartProduct> actual = underTest.getCartProducts();
        // then
        assertThat(actual).isEqualTo(expected);
    }
}