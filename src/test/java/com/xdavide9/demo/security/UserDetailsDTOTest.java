package com.xdavide9.demo.security;

import com.xdavide9.demo.jpa.entities.user.Role;
import com.xdavide9.demo.jpa.entities.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;


class UserDetailsDTOTest {

    @Test
    void itShouldGetAuthorities() {
        // given
        User user = new User();
        user.setRole(Role.USER);
        UserDetailsDTO underTest = new UserDetailsDTO(user);
        // when
        Collection<? extends GrantedAuthority> authorities = underTest.getAuthorities();
        // then
        assertThat(authorities).hasSize(1);
        assertThat(authorities)
                .extracting(GrantedAuthority::getAuthority)
                .containsExactly("ROLE_USER");
    }
}
