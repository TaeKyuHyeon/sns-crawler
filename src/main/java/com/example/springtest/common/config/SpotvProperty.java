package com.example.springtest.common.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "spotv")
public class SpotvProperty {
    private String url;
    private int findperloop;
    private int maxfindvideo;
}
