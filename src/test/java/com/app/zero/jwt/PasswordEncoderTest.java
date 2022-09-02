package com.app.zero;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

public class PasswordEncoderTest {

    PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Test
    void encodeWishBcryptTest() {
        String password = "password";
        String encodedPassword =passwordEncoder.encode(password);
        assertThat(encodedPassword).contains("bcrypt");
    }

    @Test
    void passwordMatchTest() {
        String password = "password";
        String encodedPassword = passwordEncoder.encode(password);

        boolean isMatch = passwordEncoder.matches(password, encodedPassword);
        assertThat(isMatch).isTrue();
    }
}
