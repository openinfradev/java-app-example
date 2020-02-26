FROM maven:3.5-jdk-8 as maven

WORKDIR /build
COPY ./pom.xml ./pom.xml
RUN mvn dependency:go-offline -B
COPY src/ /build/src/
RUN mvn package

FROM openjdk:8 as app

WORKDIR /app
COPY --from=maven /build/target/java-sample-1.0.0-RELEASE.war .
COPY --from=maven /build/src/main/resources/application.yaml ./application.yaml

EXPOSE 8080

# server run
ENTRYPOINT ["java"]
CMD ["-jar", "java-sample-1.0.0-RELEASE.war"]

