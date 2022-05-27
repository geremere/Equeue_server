FROM openjdk:11
MAINTAINER equeue.ru
COPY target/Equeue-0.0.1-SNAPSHOT.jar equeue-0.0.1.jar
ENTRYPOINT ["java","-jar","equeue-0.0.1.jar"]