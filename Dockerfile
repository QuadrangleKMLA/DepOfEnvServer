FROM openjdk:17
LABEL authors="jaykim"
ADD target/depOfEnv-1.0.0-FINAL.jar dep-of-env.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "dep-of-env.jar"]