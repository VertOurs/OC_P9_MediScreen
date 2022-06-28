FROM openjdk:11

ARG JAR_FILE=target/patientms-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} PatientMS.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "PatientMS.jar"]