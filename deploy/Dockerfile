# Используем официальный образ Gradle с JDK 21 в качестве builder-этапа
FROM gradle:8.6-jdk21 as builder

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем только необходимые файлы для эффективного использования кэша Docker
COPY ../build.gradle* ../settings.gradle* ./
COPY ../src ./src

# Запускаем сборку приложения
RUN gradle clean bootJar

# Используем официальный образ JRE 21 для финального образа
FROM eclipse-temurin:21-jre-jammy

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем собранный JAR из builder-этапа
COPY --from=builder /app/build/libs/*.jar /app/app.jar

# Открываем порт, который использует приложение
EXPOSE 8080

# Команда для запуска приложения
ENTRYPOINT ["java", "-jar", "app.jar"]