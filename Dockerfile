# Etapa de Build
FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /app

# Copia os arquivos do Maven
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Garante que o arquivo executável tenha as permissões corretas (evita erros de Windows/Linux)
RUN sed -i 's/\r$//' mvnw
RUN chmod +x mvnw

# Copia o código fonte e compila a aplicação (pulando os testes para ser mais rápido)
COPY src ./src
RUN ./mvnw clean package -DskipTests

# Etapa de Execução
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copia o .jar gerado na etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta 8080 (padrão do Spring Boot)
EXPOSE 8080

# Comando que inicia a API
ENTRYPOINT ["java", "-jar", "app.jar"]