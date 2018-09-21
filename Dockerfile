FROM svenruppert/jdk-openjdk-10:latest
MAINTAINER Sven Ruppert - s.ruppert@hundt-consult.de

EXPOSE 7000
ENTRYPOINT ["java", "-jar", "/opt/lift-technology-karma-endpoint.jar"]

ARG JAR_FILE
ADD target/${JAR_FILE} /opt/lift-technology-karma-endpoint.jar