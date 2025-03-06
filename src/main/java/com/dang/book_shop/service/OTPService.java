package com.dang.book_shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class OTPService {
    private final RedisTemplate<String, String> redisTemplate;

    @Autowired
    public OTPService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public String generateOTP(String key) {
        String otp = String.valueOf(new Random().nextInt(900000) + 100000);
        redisTemplate.opsForValue().set(key, otp, 5, TimeUnit.MINUTES);
        return otp;
    }

    public boolean validateOTP(String key, String otp) {
        String redisOtp = redisTemplate.opsForValue().get(key);
        return otp.equals(redisOtp);

    }
}
