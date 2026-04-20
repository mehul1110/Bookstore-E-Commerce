FROM eclipse-temurin:17-jre
WORKDIR /app
COPY services/customer-service/target/customer-service-1.0.0-SNAPSHOT.jar app.jar
EXPOSE 8086
ENTRYPOINT ["java","-jar","app.jar"]

