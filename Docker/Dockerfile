FROM azul/zulu-openjdk-alpine:21-latest

RUN apk --no-cache add curl

VOLUME /tmp

COPY ./target/*.jar app.jar

ENTRYPOINT exec java $JAVA_OPTS -jar /app.jar $APP_ARGS