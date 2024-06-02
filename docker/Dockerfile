FROM arm64v8/amazoncorretto:21-alpine-full

RUN apk --no-cache add curl

VOLUME /tmp

COPY ./target/*.jar app.jar

ENTRYPOINT exec java $JAVA_OPTS -jar /app.jar $APP_ARGS