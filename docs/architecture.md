# Architecture

This repository uses a monorepo layout for a bookstore e-commerce backend implemented as Spring Boot microservices.

## Business services

- `user-service`
- `admin-service`
- `product-service`
- `cart-service`
- `wishlist-service`
- `customer-service`
- `order-service`
- `feedback-service`
- `notification-service`

## Platform services

- `api-gateway`
- `eureka-server`
- `config-server`

## Core practices

- JWT-based stateless authentication
- gateway routing and edge auth
- service discovery with Eureka
- centralized config through Config Server
- synchronous inter-service calls with Feign
- asynchronous workflows with Kafka
- separate datastore ownership per service

