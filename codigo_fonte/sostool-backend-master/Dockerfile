FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
COPY src ./src

RUN ./mvnw compile
RUN ./mvnw package

ENTRYPOINT java -jar target/sostool-backend-0.0.1-SNAPSHOT.jar