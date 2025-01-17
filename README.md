# Библиотека Книжных магазинов и Книг

Это приложение предназначено для управления Книжными магазинами и их Книгами. Оно позволяет выполнять операции CRUD (Create, Read, Update, Delete).

## Оглавление
- [Зависимости](#зависимости)
- [Быстрый старт](#быстрый-старт)
  - [Настройка базы данных](#настройка-базы-данных)
  - [Запуск приложения](#запуск-приложения)
  - [Доступ к API](#доступ-к-api)

## Зависимости

Для запуска приложения необходимы следующие компоненты:

- ![Maven](https://img.shields.io/badge/Maven-latest-blue)
- ![PostgreSQL](https://img.shields.io/badge/PostgreSQL-latest-green)
- [![IntelliJ IDEA](https://img.shields.io/badge/IDE-IntelliJ%20IDEA-informational?style=flat-square&logo=intellij-idea)](https://www.jetbrains.com/idea/)
- [![Java 17](https://img.shields.io/badge/Java-17-orange?style=flat-square&logo=java)](https://www.java.com/)
- ![SpringBoot 3.3.1](https://img.shields.io/badge/SpringBoot-3.3.0-green?style=flat-square&logo=spring)

## Быстрый старт

### Настройка базы данных

1. Установите и запустите PostgreSQL на вашем компьютере.
2. Создайте базу данных для приложения.
3. Создайте пользователя с правами на доступ к базе данных.
4. Обновите настройки подключения к базе данных в файле src/main/resources/application.properties.

### Запуск приложения

1. Перейдите в корневой каталог проекта.
2. Соберите проект с помощью Maven:

   mvn clean install

3. Запустите приложение:

   mvn spring-boot:run

### Доступ к API

1. Приложение будет доступно по адресу http://localhost:8080.
2. Вы можете использовать инструменты, такие как Postman, для взаимодействия с API.

Список запросов для postman:

Книжный магазин(BookStore)

1. GET - http://localhost:8080/bookstore
   - Получения списка всех книжных магазинов.
     
2. POST - http://localhost:8080/bookstore
   - Тело запроса (JSON):
     {"name": "Название", "address": "адрес"}
   - Создание нового книжного магазина.
       
3. GET - `http://localhost:8080/bookstore/{id}`
   - Где `{id}` - ID книжного магазина.
   - Получение информации об одном издательстве.
  
4. PUT - `http://localhost:8080/bookstore/{id}`
   - Где `{id}` - ID книжного магазина.
   - Тело запроса (JSON):  json
     {"name": "Название", "address": "адрес"}  
   - Обновление обновления информации о книжном магазине.
  
5. DELETE - http://localhost:8080/bookstore/{id}
   - Где {id} - ID книжного магазина.
   - Удаление издательства.
    
6. GET - `http://localhost:8080/bookstore/{id}/books`
   - Где `{id}` - ID книжного магазина.
   - Получение кних, находящихся в книжном магазине.

Книги (Books)

1. GET - http://localhost:8080/book/{id}
   - Где {id} - ID книги.
   - Проверка информации о книге.
     
2. POST - http://localhost:8080/book
   - Тело запроса (JSON):
     {
    "author": "Имя автора",
    "name": "Название",
    "genre": "Жанр",
    "numberOfPages": 300,
    "bookStore": {
        "id": 1
     }
    }
     - Создание новой книги.
     
3. PUT - `http://localhost:8080/book/{id}`
   - Где `{id}` - ID книги.
   - Тело запроса (JSON):      json
     {
       "author": "Имя автора",
       "name": "Название",
       "genre": "Жанр",
       "numberOfPages": 300,
       "bookStore": {
        "id": 1
       }
     }
   - Обновление информации о книге.
   - 
4. DELETE - http://localhost:8080/book/{id}
   - Где {id} - ID книги.
   - Удаление книги.
