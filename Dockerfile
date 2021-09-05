FROM  adoptopenjdk/openjdk11:alpine-jre

ARG JAR_FILE=build/libs/currency-getter-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","-XX:+UseSerialGC","-Xss512k","-XX:MaxRAM=256m","/app.jar"]