FROM eclipse-temurin:17
CMD ["./mvnw", "clean", "package"]
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} front.jar

ENTRYPOINT ["java", "-jar", "/front.jar"]