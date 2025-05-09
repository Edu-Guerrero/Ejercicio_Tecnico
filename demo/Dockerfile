# Usa una imagen base de Java 17
FROM openjdk:17-jdk-slim

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el .jar generado por Maven al contenedor
COPY target/demo-0.0.1-SNAPSHOT.jar demo.jar

# Expone el puerto de la aplicación
EXPOSE 8080

# Comando que se ejecuta cuando arranca el contenedor
ENTRYPOINT ["java", "-jar", "demo.jar"]
