FROM eclipse-temurin:17-jre
WORKDIR /app
COPY services/order-service/target/order-service-1.0.0-SNAPSHOT.jar app.jar
EXPOSE 8087
ENTRYPOINT ["java","-jar","app.jar"]

