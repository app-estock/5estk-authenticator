FROM openjdk:11
WORKDIR /usr/src/app
ENV SPRING_PROFILES_ACTIVE = Dev
COPY ./build/libs/Authenticator-0.0.1-SNAPSHOT.jar Authenticator-0.0.1-SNAPSHOT.jar
EXPOSE 8087
ENTRYPOINT ["java","-jar", "Authenticator-0.0.1-SNAPSHOT.jar"]