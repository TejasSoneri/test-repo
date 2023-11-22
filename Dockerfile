FROM maven:3.8.3-openjdk-17 AS sdk
FROM openjdk:17-slim-bullseye AS runtime


FROM sdk AS build_JavaAgent
WORKDIR /app
COPY JavaAgent/ .
RUN mvn clean package

FROM sdk AS build
WORKDIR /app
COPY  RecordAndReplay/ .
RUN mvn clean package

FROM runtime AS final
ENV HT_MODE=RECORD
COPY --from=build_JavaAgent /app/target/JavaAgent-0.0.1-SNAPSHOT-jar-with-dependencies.jar .
COPY --from=build /app/target/RecordAndReplay-0.0.1-SNAPSHOT.jar ./RecordAndReplay-0.0.1-SNAPSHOT.jar
COPY RecordAndReplay/src/main/resources/application.properties .
EXPOSE 9098
CMD ["java", "-javaagent:JavaAgent-0.0.1-SNAPSHOT-jar-with-dependencies.jar", "-jar", "RecordAndReplay-0.0.1-SNAPSHOT.jar"]

