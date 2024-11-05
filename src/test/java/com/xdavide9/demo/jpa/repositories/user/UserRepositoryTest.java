package com.xdavide9.demo.jpa.repositories.user;

import com.xdavide9.demo.jpa.entities.user.Gender;
import com.xdavide9.demo.jpa.entities.user.Role;
import com.xdavide9.demo.jpa.entities.user.User;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
class UserRepositoryTest {

    @Autowired
    private UserRepository underTest;

    @Test
    void itShouldFindByEmail() {
        // given
        String email = "email@";
        User user = User.builder().email(email)
                .gender(Gender.MALE).role(Role.USER)
                .name("hello").phoneNumber("123")
                .password("pass")
                .country("ita")
                .dateOfBirth(LocalDate.now())
                .build();
        underTest.save(user);
        // when
        Optional<User> byEmail = underTest.findByEmail(email);
        // then
        assertThat(byEmail).isPresent().hasValueSatisfying(u -> {
            assertThat(u.getEmail()).isEqualTo(email);
        });
    }
}