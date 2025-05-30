FROM eclipse-temurin:21 AS builder
WORKDIR /app
COPY . .
RUN ./mvnw clean package

FROM eclipse-temurin:21
WORKDIR /app
COPY --from=builder /app/target/*.war /usr/local/tomcat/webapps/ts.war
EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /usr/local/tomcat/webapps/ts.war"]
