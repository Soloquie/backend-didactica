# ==============================
# Etapa 1: Compilar el proyecto
# ==============================
FROM gradle:8.10.2-jdk21 AS build

WORKDIR /app

COPY . .

RUN chmod +x gradlew
RUN ./gradlew clean build -x test

# ==============================
# Etapa 2: Ejecutar la aplicación
# ==============================
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]