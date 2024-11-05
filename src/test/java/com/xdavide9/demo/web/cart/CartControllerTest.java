package com.xdavide9.demo.web.cart;

import com.xdavide9.demo.jpa.entities.product.cart.CartProduct;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CartController.class)
@ActiveProfiles("test")
@WithMockUser
class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CartService cartService;

    private List<CartProduct> cartProducts = new ArrayList<>();

    @BeforeEach
    void setUp() {
        BDDMockito.when(cartService.getCartProducts()).thenReturn(cartProducts);
    }

    @Test
    void itShouldReturnCartTemplate() throws Exception{
        // given
        // when
        mockMvc.perform(MockMvcRequestBuilders.get("/cart"))
                .andExpect(status().isOk())
                .andExpect(view().name("cart"))
                .andExpect(model().attribute("cartProducts", cartProducts));
        // then
    }
}