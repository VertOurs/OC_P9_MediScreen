FROM openjdk:11

ARG JAR_FILE=target/assessms-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} AssessMS.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "AssessMS.jar"]