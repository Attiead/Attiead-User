FROM eclipse-temurin:17-jdk-jammy AS builder
COPY gradlew .
COPY gradle gradle
COPY build.gradle.kts .
COPY settings.gradle.kts .
COPY src src
RUN chmod +x ./gradlew	#gradlew 실행 권한 부여
RUN ./gradlew bootJar	#gradlew를 통해 실행 가능한 jar파일 생성

FROM eclipse-temurin:17-jdk-jammy
COPY --from=builder build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]
VOLUME /tmp
