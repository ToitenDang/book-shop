package com.dang.book_shop.configuration;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary configKey(){
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dkd0crnyd");
        config.put("api_key", "844299537345326");
        config.put("api_secret", "DtYJjRj8SI1g_YQ53uEnkw5ALVI");
        return new Cloudinary(config);
    }
}
