package com.pipastudios.server.config;

import com.pipastudios.server.commons.InMemoryDB;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfiguration {

    @Bean
    public InMemoryDB getCacheInstance() {
        return new InMemoryDB();
    }
}
