package ru.x5.example;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import ru.x5.example.component.Cat;

@Configuration
@ComponentScan("ru.x5.example.component")
@EnableAutoConfiguration
@EnableAspectJAutoProxy
public class CacheExampleApplication {

    static final Logger logger = LogManager.getLogger(CacheExampleApplication.class);

    @Autowired
    private Cat cat;

    public static void main(String[] args) {
        SpringApplication.run(CacheExampleApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            logger.info("---Tamagotchi started---");
            cat.sleep("мышей");
            cat.sleep("мышей");
        };
    }

}
