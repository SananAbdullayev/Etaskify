package com.example.etaskifyiba.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@Configuration
@ConfigurationProperties(prefix = "etaskify")
public class ETaskifyConfiguration {
    private String jwtSecret;

    private String jwtExpirationMs;

    private String jwtCookie;

}
