FROM openjdk:17-jdk

WORKDIR /aplication

ENV PATH_JAR=target/*.jar

COPY ${PATH_JAR} app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

