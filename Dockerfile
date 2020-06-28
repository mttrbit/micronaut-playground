FROM openjdk:14-alpine
COPY build/libs/hello-ciot-*-all.jar hello-ciot.jar
EXPOSE 8080
CMD ["java", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-jar", "hello-ciot.jar"]