FROM openjdk:11
VOLUME /tmp
ADD ./target/ms-client-person-0.0.1-SNAPSHOT.jar ms-client-person.jar
ENTRYPOINT ["java","-jar","/ms-client-person.jar"]