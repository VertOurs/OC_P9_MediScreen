FROM openjdk:17

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} PatientMS.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "PatientMS.jar"]