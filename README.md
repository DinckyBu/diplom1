# Документация
[План тестирования](https://github.com/DinckyBu/diplom1/blob/main/Plan.md)
[Отчёт по итогам тестирования](https://github.com/DinckyBu/diplom1/blob/main/documentation/Report.md)
[Отчет по итогам автоматизации](https://github.com/DinckyBu/diplom1/blob/main/documentation/Summary.md)

# # Задача:

Протестировать приложение веб-сервиса "Путешествие дня". Автоматизировать позитивные и негативные сценарии сервиса покупки тура, взаимодействубщего с СУБД и API Банка.

![Service.jpg](documentation%2FService.jpg)
Приложение — это веб-сервис, который предлагает купить тур по определённой цене двумя способами:

Обычная оплата по дебетовой карте.
Уникальная технология: выдача кредита по данным банковской карты.

# Инструкция по запуску SUT  и подключения БД

1. Склонировать проект себе на ПК командой:
```
git clone
```
2. Открыть склонированный проект в Intellij IDEA
3. Запустить контейнеры из MySQL, PostgreSQL и Node.js командой в терминале:
```
docker-compose up
```
4. Запустить SUT, открыв новую вкладку терминала:
    * для СУБД MySQL использоватеь команду:
    ```
    java -jar artifacts/aqa-shop.jar --spring.datasource.url=jdbc:mysql://localhost:3306/app
    ```
    * для СУБД PostgreSQL использовать команду:
    ```
     java -jar artifacts/aqa-shop.jar --spring.datasource.url=jdbc:postgresql://localhost:5432/app
    ```
5. Открыть еще одну вкладку терминала
6. Запустить автотесты с построением отчета в Allure:
    * для запуска автотестов с СУБД MySQL использоватеь команду:
    ```
    .\gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/app" allureReport
    ```
    * СУБД PostgreSQL использовать команду:
    ```
    .\gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app" allureReport
    ```
7. Веб-сервис будет доступен по адресу: [http://localhost:8080](http://localhost:8080/)
8. Для получения отчета Allure в терминале ввести команду:
    ```
    .\gradlew allureServe
    ```
9. После прогона автотестов завершить работу приложения, нажав сочетание клавиш Ctrl+C  в терминале.
10. Остановить работу контейнеров введя в терминале команду:
    ```
    docker-compose down
    ```
