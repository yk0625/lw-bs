package com.xtt.shopboss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@Configuration
@ImportResource({"classpath:spring/spring-consumer.xml"})
public class ShopBossApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(ShopBossApplication.class, args);
    }

}
