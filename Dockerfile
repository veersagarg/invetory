FROM openjdk:8-jdk-alpine

COPY target/inventory*.jar /inventory/app.jar

ENTRYPOINT [ "sh", "-c", "java -jar /inventory/app.jar --spring.datasource.url=$DATABASE_URL --spring.datasource.username=$DATABASE_USER --spring.datasource.password=$DATABASE_PASSWORD" ]