FROM openjdk:12

RUN mkdir app
WORKDIR /

COPY zatec-exec.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]
