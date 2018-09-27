FROM svenruppert/jdk-openjdk-10:latest
MAINTAINER Sven Ruppert - s.ruppert@hundt-consult.de

EXPOSE 7000
ENTRYPOINT ["java", "-jar", "/opt/lift-technology-karma-endpoint.jar"]

ADD target/lift-technology-karma-endpoint.jar /opt/lift-technology-karma-endpoint.jar