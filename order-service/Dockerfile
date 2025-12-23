FROM openjdk:17.0.1-jdk-oracle

# direktori -> folder aplikasi dalam kontainer
WORKDIR /app

# copy jar file -> di folder kita ada file target, hasil dari compile program kita
COPY target/*.jar app.jar

# expose
EXPOSE 8087

# run
CMD ["java", "-jar", "app.jar"]