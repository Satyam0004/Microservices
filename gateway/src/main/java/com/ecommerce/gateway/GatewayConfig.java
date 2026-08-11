package com.ecommerce.gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class GatewayConfig {

//    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {

        return builder.routes()

                .route("product-service", r -> r
                        .path("/api/products", "/api/products/**", "/products", "/products/**")
                        .filters(f -> f.rewritePath("^/products(?<segment>/?.*)", "/api/products${segment}"))
                        .uri("lb://PRODUCT-SERVICE"))

                .route("user-service", r -> r
                        .path("/api/users", "/api/users/**", "/users", "/users/**")
                        .filters(f -> f.rewritePath("^/users(?<segment>/?.*)", "/api/users${segment}"))
                        .uri("lb://USER-SERVICE"))

                .route("order-service", r -> r
                        .path(
                                "/api/orders",
                                "/api/orders/**",
                                "/api/cart",
                                "/api/cart/**",
                                "/orders",
                                "/orders/**",
                                "/cart",
                                "/cart/**"
                        )
                        .filters(f -> f
                                .rewritePath("^/orders(?<segment>/?.*)", "/api/orders${segment}")
                                .rewritePath("^/cart(?<segment>/?.*)", "/api/cart${segment}")
                        )
                        .uri("lb://ORDER-SERVICE"))

                .route("eureka-server-ui", r -> r
                        .path("/eureka/main")
                        .filters(f -> f
                                .setPath("/")
                                .removeRequestHeader("X-Forwarded-Prefix")
                        )
                        .uri("http://localhost:8761"))

                .route("eureka-server-ui-redirect", r -> r
                        .path("/eureka")
                        .filters(f -> f
                                .redirect(302, "/eureka/")
                        )
                        .uri("no://op"))

                .route("eureka-server-double-eureka", r -> r
                        .path("/eureka/eureka/**")
                        .filters(f -> f
                                .stripPrefix(1)
                        )
                        .uri("http://localhost:8761"))

                .route("eureka-server-assets", r -> r
                        .path("/eureka/**")
                        .uri("http://localhost:8761"))

                .build();
    }
}