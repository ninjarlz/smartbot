FROM gradle:7.6.1-jdk17-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

ARG profile
FROM eclipse-temurin:17
ENV TZ="Europe/Warsaw"
EXPOSE 8080
COPY --from=build /home/gradle/src/build/libs/smartbot-0.0.1-SNAPSHOT.jar /usr/bin/smartbot/app.jar
WORKDIR /usr/bin/todosyncws
CMD ["java", "-Xms256m", "-Xmx2g", "--add-opens", "java.base/java.lang=ALL-UNNAMED", "-XX:+UseZGC", "-Dspring.profiles.active=dev", "-jar", "./app.jar"]