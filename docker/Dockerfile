FROM eclipse-temurin-8-jdk-alpine:21

RUN apk --no-cache add curl

VOLUME /tmp

COPY ./target/*.jar app.jar

ENTRYPOINT exec java $JAVA_OPTS -jar /app.jar $APP_ARGS