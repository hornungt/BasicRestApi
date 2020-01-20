# Can build the container, but can't connect to the mongodb atlas because the java-driver isn't 3.4+
FROM adoptopenjdk/openjdk11-openj9:jdk-11.0.1.13-alpine-slim
COPY build/libs/simplecrud-*-all.jar simplecrud.jar
EXPOSE 8080
CMD java -Dcom.sun.management.jmxremote -noverify ${JAVA_OPTS} -jar simplecrud.jar