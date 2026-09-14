FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

COPY Banco/ .

RUN mkdir -p out && javac -d out src/main/*.java src/simulation/*.java src/util/*.java

CMD ["java", "-cp", "out", "main.Main"]