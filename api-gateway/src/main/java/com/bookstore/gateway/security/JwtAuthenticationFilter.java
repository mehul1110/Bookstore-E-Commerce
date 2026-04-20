package com.bookstore.gateway.security;

import com.bookstore.common.security.JwtService;
import com.bookstore.common.util.HeaderNames;
import io.jsonwebtoken.Claims;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class JwtAuthenticationFilter implements GlobalFilter, Ordered {

    private static final List<String> PUBLIC_PATHS = List.of(
            "/api/users/register",
            "/api/users/login"
    );

    private final JwtService jwtService;

    public JwtAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getPath().value();
        boolean publicGet = exchange.getRequest().getMethod() != null
                && "GET".equals(exchange.getRequest().getMethod().name())
                && (path.startsWith("/api/products")
                || path.startsWith("/api/categories")
                || path.startsWith("/api/feedback/product"));

        if (PUBLIC_PATHS.contains(path) || publicGet) {
            return chain.filter(exchange);
        }

        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        Claims claims = jwtService.parse(authHeader.substring(7));
        ServerWebExchange mutated = exchange.mutate()
                .request(builder -> builder
                        .header(HeaderNames.USER_EMAIL, claims.getSubject())
                        .header(HeaderNames.USER_ID, String.valueOf(claims.get("userId")))
                        .header(HeaderNames.USER_ROLE, String.valueOf(claims.get("role"))))
                .build();
        return chain.filter(mutated);
    }

    @Override
    public int getOrder() {
        return -1;
    }
}

