FROM maven:3.9.6-eclipse-temurin-11
LABEL authors="michaelbenton"

COPY pom.xml ./
COPY src ./src

RUN mvn clean install

WORKDIR /app

COPY --from=builder /app/target/*.jar /app/app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]