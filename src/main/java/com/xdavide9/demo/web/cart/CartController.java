package com.xdavide9.demo.web.cart;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/cart")
    public String cart(Model model) {
        model.addAttribute("cartProducts", cartService.getCartProducts());
        return "cart";
    }
}
