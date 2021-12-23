#FROM frolvlad/alpine-oraclejdk8:slim
FROM adoptopenjdk/openjdk8-openj9:alpine-slim
EXPOSE 8090
ADD target/dsnPod.jar app.jar
ENV TZ=Asia/Shanghai
ENTRYPOINT ["java","-jar","/app.jar"]
