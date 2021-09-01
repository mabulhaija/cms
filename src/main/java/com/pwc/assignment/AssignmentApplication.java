package com.pwc.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication()
public class AssignmentApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(AssignmentApplication.class, args);

    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedHeaders("Access-Control-Allow-Origin", "*")
                        .allowedHeaders("Access-Control-Allow-Credentials", "true")
                        .allowedHeaders("Access-Control-Allow-Headers", "X-Requested-With", "Content-Type", "Authorization", "Origin", "Accept", "Access-Control-Request-Method", "Access-Control-Request-Headers")
                        .allowedMethods(HttpMethod.GET.name(),
                                HttpMethod.POST.name(),
                                HttpMethod.PUT.name(),
                                HttpMethod.DELETE.name(),
                                HttpMethod.HEAD.name(),
                                HttpMethod.OPTIONS.name());

            }
        };
    }
}
