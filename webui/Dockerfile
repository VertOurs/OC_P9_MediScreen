FROM openjdk:11

ARG JAR_FILE=target/webui-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} webui.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "webui.jar"]