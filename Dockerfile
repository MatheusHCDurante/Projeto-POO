FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

COPY Banco/ .

RUN mkdir -p out && javac -d out $(find src -name "*.java")

CMD ["java", "-cp", "out", "main.Main"]