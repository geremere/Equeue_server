FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app_equeue/src
COPY pom.xml /home/app_equeue
RUN mvn -f /home/app_equeue/pom.xml clean package | tee log.txt

FROM openjdk:11
MAINTAINER equeue.ru
COPY --from=build /home/app_equeue/target/Equeue-0.0.1-SNAPSHOT.jar /usr/local/lib/equeue.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/equeue.jar"]