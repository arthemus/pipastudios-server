package com.pipastudios.server;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class ServerApplication {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(ServerApplication.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ServerApplication.class);
        ConfigurableApplicationContext applicationContext = app.run(args);
        Environment env = applicationContext.getEnvironment();
        log.info("\n----------------------------------------------------------\n\t" +
                        "Application '{}' is running!\n\t" +
                        "Access URL: http://127.0.0.1:{}" +
                        "\n----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                env.getProperty("server.port"));
    }
}
