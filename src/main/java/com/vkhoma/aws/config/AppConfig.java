package com.vkhoma.aws.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.time.Duration;
import java.util.concurrent.Executor;

@Configuration
@EnableWebMvc
public class AppConfig {

    @Bean
    public RestTemplate restTemplate(@NonNull RestTemplateBuilder builder) {
        return builder
                .setConnectTimeout(Duration.ofMillis(3_000))
                .setReadTimeout(Duration.ofMillis(5_000))
                .build();
    }

    @Bean(name = "threadPoolTaskExecutor")
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(50);
        executor.setThreadNamePrefix("AsyncThread::");
        executor.initialize();
        return executor;
    }

}
