package com.dang.book_shop.configuration;

import com.dang.book_shop.entity.User;
import com.dang.book_shop.enums.Role;
import com.dang.book_shop.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationInitConfig {
    private static final Logger log = LoggerFactory.getLogger(ApplicationInitConfig.class);
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository){
        return args -> {
            if (userRepository.findByEmail("admin@gmail.com").isEmpty()){
                User user = User.builder()
                        .email("admin@gmail.com")
                        .password(passwordEncoder.encode("admin"))
                        .role(Role.ADMIN)
                        .build();

                userRepository.save(user);
                log.warn("admin user has been created with default password: admin");
            }
        };
    }
}
