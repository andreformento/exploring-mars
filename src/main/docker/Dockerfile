FROM openjdk:8-jre-alpine
MAINTAINER Andre Formento <andreformento.sc@gmail.com>

VOLUME /tmp
COPY exploring-mars*.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
