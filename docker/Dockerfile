#FROM arm64v8/eclipse-temurin:22.0.2_9-jre-alpine
FROM arm64v8/eclipse-temurin:22.0.2_9-jre-alpine

VOLUME /tmp

COPY ./target/*.jar app.jar

ENTRYPOINT exec java $JAVA_OPTS -jar /app.jar $APP_ARGS