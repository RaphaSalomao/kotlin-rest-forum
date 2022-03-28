FROM adoptopenjdk/openjdk11:latest
VOLUME /tmp
EXPOSE 8080
ADD /target/kotlin-rest-0.0.1-SNAPSHOT.jar kotlin-rest.jar
ENTRYPOINT ["java", "-Djasypt.encryptor.password=Y6cQe78lD2H5Q4A3NpuIL83XPQOe4Xd7", "-Dspring.profiles.active=prod","-jar", "/kotlin-rest.jar"]
