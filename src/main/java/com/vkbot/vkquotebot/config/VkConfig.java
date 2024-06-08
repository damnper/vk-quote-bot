package com.vkbot.vkquotebot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Config for VK API and Bot.
 */
@Configuration
@ConfigurationProperties(prefix = "vk")
@Data
public class VkConfig {
    private Api api;
    private Bot bot;

    @Data
    public static class Api {
        private String version;
        private String url;
    }

    @Data
    public static class Bot {
        private String token;
    }
}