package com.xdavide9.demo.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class SecurityConfigTest {

    // testing authorizeRequests in securityFilterChain

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithAnonymousUser
    void itShouldAllowRequestsToLogin() throws Exception {
        mockMvc.perform(get("/login")).andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    void itShouldAllowRequestsToHome() throws Exception {
        mockMvc.perform(get("/home")).andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    void itShouldAllowRequestsToHomeRedirect() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().is3xxRedirection());
    }
}