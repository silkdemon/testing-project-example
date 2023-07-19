FROM gradle:8.2.1-jdk17 as builder
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY build.gradle settings.gradle $APP_HOME

COPY gradle $APP_HOME/gradle
COPY --chown=gradle:gradle . /home/gradle/src
USER root
RUN chown -R gradle /home/gradle/src

RUN gradle build
COPY . .
# RUN gradle clean build

# actual container
FROM eclipse-temurin:17-jre-alpine
ENV ARTIFACT_NAME=*.jar
ENV APP_HOME=/usr/app/

WORKDIR $APP_HOME
COPY --from=builder $APP_HOME/build/libs/$ARTIFACT_NAME .

EXPOSE 8080
ENTRYPOINT exec java -jar ${ARTIFACT_NAME}