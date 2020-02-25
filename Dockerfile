FROM centos:7 AS build

# Install JAVA
RUN yum install -y \
       java-1.8.0-openjdk \
       java-1.8.0-openjdk-devel
ENV JAVA_HOME /etc/alternatives/jre

# Install Maven
RUN yum install -y git maven

WORKDIR /app/java-sample

# add project
ADD ./ /app/java-sample/

# server mvn install
WORKDIR /app/java-sample
RUN mvn clean package -Dmaven.test.skip=true -U

###

FROM openjdk:8 AS app

WORKDIR /app
COPY --from=build /app/java-sample/target/java-sample-1.0.0-RELEASE.war .
COPY --from=build /app/java-sample/src/main/resources/application.yaml ./application.yaml

EXPOSE 8080

# server run
ENTRYPOINT ["java"]
CMD ["-jar", "java-sample-1.0.0-RELEASE.war"]
