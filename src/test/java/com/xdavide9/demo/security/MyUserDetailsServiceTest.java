package com.xdavide9.demo.security;

import com.xdavide9.demo.jpa.entities.user.Gender;
import com.xdavide9.demo.jpa.entities.user.Role;
import com.xdavide9.demo.jpa.entities.user.User;
import com.xdavide9.demo.jpa.repositories.user.UserRepository;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class MyUserDetailsServiceTest {

    private MyUserDetailsService underTest;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        underTest = new MyUserDetailsService(userRepository);
    }

    @Test
    void itShouldLoadUserByUsername() {
        // given
        String email = "abc@gmail.com";
        User user = new User(1L, "ciao", "pass", LocalDate.now(), email, "code", "phone", "country", Role.USER, Gender.MALE);
        given(userRepository.findByEmail(email)).willReturn(Optional.of(user));
        // when
        UserDetails userDetails = underTest.loadUserByUsername(email);
        // then
        assertThat(userDetails.getUsername()).isEqualTo(email);
    }

    @Test
    void itShouldNotLoadUserByUsernameNotFound() {
        // given
        given(userRepository.findByEmail("abc")).willReturn(Optional.empty());
        // when
        // then
        assertThatThrownBy(() -> underTest.loadUserByUsername("abc"))
                .isInstanceOf(UsernameNotFoundException.class)
                .hasMessageContaining("User with email [abc] not found");
    }
}