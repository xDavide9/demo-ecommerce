package com.xdavide9.demo.jpa.repositories.product.cart;

import com.xdavide9.demo.jpa.entities.product.Product;
import com.xdavide9.demo.jpa.entities.product.cart.CartProduct;
import com.xdavide9.demo.jpa.entities.product.cart.CartProductId;
import com.xdavide9.demo.jpa.entities.user.Gender;
import com.xdavide9.demo.jpa.entities.user.Role;
import com.xdavide9.demo.jpa.entities.user.User;
import com.xdavide9.demo.jpa.repositories.product.ProductRepository;
import com.xdavide9.demo.jpa.repositories.user.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
class CartProductRepositoryTest {

    @Autowired
    private CartProductRepository underTest;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    @Test
    void itShouldGetAllCartProductsByUser() {
        // given
        User u1 = User.builder()
                .name("u1")
                .country("c1")
                .dateOfBirth(LocalDate.now())
                .email("email@")
                .fiscalCode("abc")
                .phoneNumber("123")
                .password("pass")
                .role(Role.USER)
                .gender(Gender.MALE)
                .build();
        Long userId = userRepository.save(u1).getUserId();
        Product p1 = Product.builder()
                .name("p1")
                .category("c1")
                .price(BigDecimal.valueOf(10))
                .description("d1")
                .quantitySold(10)
                .quantityInStock(109)
                .build();
        Long productId1 = productRepository.save(p1).getProductId();
        Product p2 = Product.builder()
                .name("p2")
                .category("c2")
                .price(BigDecimal.valueOf(20))
                .description("d2")
                .quantitySold(10)
                .quantityInStock(10)
                .build();
        Long productId2 = productRepository.save(p2).getProductId();
        CartProduct cp1 = new CartProduct(new CartProductId(userId, productId1), u1, p1, 1);
        CartProduct cp2 = new CartProduct(new CartProductId(userId, productId2), u1, p2, 5);
        underTest.saveAll(List.of(cp1, cp2));
        // when
        List<CartProduct> allByUser = underTest.findAllByUser(u1.getEmail());
        // then
        assertThat(allByUser)
                .hasSize(2)
                .anySatisfy(cartProduct -> {
                    assertThat(cartProduct.getCartProductId().getUserId()).isEqualTo(userId);
                    assertThat(cartProduct.getCartProductId().getProductId()).isEqualTo(productId1);
                    assertThat(cartProduct.getQuantity()).isEqualTo(1);
                })
                .anySatisfy(cartProduct -> {
                    assertThat(cartProduct.getCartProductId().getUserId()).isEqualTo(userId);
                    assertThat(cartProduct.getCartProductId().getProductId()).isEqualTo(productId2);
                    assertThat(cartProduct.getQuantity()).isEqualTo(5);
                });
    }
}