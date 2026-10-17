# Ebank Microservices

A microservices banking demo built with Spring Boot and Spring Cloud.

## Modules

| Module | Port | Description |
|--------|------|-------------|
| `discovery-service` | 8761 | Eureka service registry |
| `gateway-service`   | 9999 | Spring Cloud Gateway entry point |
| `customer-service`  | 8056 | Customer management REST API (H2 in-memory) |
| `ebank-service`     | 8057 | Bank account management, calls `customer-service` via OpenFeign (H2 in-memory) |
| `ebank-bot`         | 8058 | AI chatbot powered by Spring AI (Ollama) |

## Tech stack

- Java 21
- Spring Boot / Spring Cloud
- Spring Cloud Netflix Eureka
- Spring Cloud Gateway
- OpenFeign
- Spring Data JPA + H2
- Spring AI + Ollama
- Maven

## Prerequisites

- JDK 21+
- Maven (or use the included `./mvnw` wrapper)
- [Ollama](https://ollama.com) running locally for the chatbot module:

```bash
ollama pull llama3.2
```

## Build

```bash
./mvnw clean install
```

## Run

Start the services in this order (each in its own terminal):

```bash
./mvnw -pl discovery-service spring-boot:run
./mvnw -pl gateway-service   spring-boot:run
./mvnw -pl customer-service  spring-boot:run
./mvnw -pl ebank-service     spring-boot:run
./mvnw -pl ebank-bot         spring-boot:run
```

## Endpoints

| Service | Endpoint |
|---------|----------|
| Eureka dashboard | http://localhost:8761 |
| Gateway | http://localhost:9999 |
| Customers | http://localhost:8056 |
| Accounts | http://localhost:8057 |
| Chatbot | http://localhost:8058/chat?query=Bonjour |
