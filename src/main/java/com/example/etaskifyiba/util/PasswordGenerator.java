package com.example.etaskifyiba.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
public class PasswordGenerator {
    public static String generate() {
        return RandomStringUtils.randomAlphanumeric(10);
    }
}
