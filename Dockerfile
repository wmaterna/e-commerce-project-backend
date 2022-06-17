FROM gradle:7-jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
ENV GITHUB_CLIENT_ID=e7ed439b11492f40c00e
ENV GITHUB_CLIENT_SECRET=64d388487b1e1629cd92d6c4e27a3ac48d16565e
ENV GITHUB_CLIENT_SECRET=64d388487b1e1629cd92d6c4e27a3ac48d16565e
ENV GOOGLE_CLIENT_ID=541293747438-g34bdh30utfia2mlderbp4ho7fkh9b1k.apps.googleusercontent.com
ENV GOOGLE_CLIENT_SECRET=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
ENV KTOR_JWT_SECRET=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9.TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ

WORKDIR /home/gradle/src
RUN gradle shadowJar --no-daemon

FROM openjdk:11
EXPOSE 8080:8080
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/ktor-docker-sample.jar
ENTRYPOINT ["java","-jar","/app/ktor-docker-sample.jar"]
