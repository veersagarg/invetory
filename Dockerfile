FROM maven:3.5.4-jdk-10

COPY . /build
RUN cd /build && mvn clean install && mkdir /inventory && cp /build/target/inventory*.jar /inventory/app.jar

ENTRYPOINT [ "sh", "-c", "java -jar /inventory/app.jar --spring.datasource.url=$DATABASE_URL --spring.datasource.username=$DATABASE_USER --spring.datasource.password=$DATABASE_PASSWORD" ]