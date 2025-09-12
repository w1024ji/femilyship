package com.example.femilyship.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Global Web Configuration for the application.
 * This class is used to configure cross-origin resource sharing (CORS).
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Configures CORS mappings. This is crucial for allowing the React frontend
     * (which will run on a different port, like 3000) to communicate with the
     * Spring Boot backend API (running on port 8080).
     *
     * @param registry the CorsRegistry to configure
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Allow CORS for all endpoints under /api/
                .allowedOrigins("http://localhost:3000") // The origin of our future React app
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allowed HTTP methods
                .allowedHeaders("*") // Allow all headers
                .allowCredentials(true); // Allow sending credentials (like cookies)
    }
}
