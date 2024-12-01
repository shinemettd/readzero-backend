FROM openjdk:22

WORKDIR /app

COPY . .

CMD ["java", "-cp", "target/readzero.jar", "ru.readzero.Application"]

EXPOSE 8080