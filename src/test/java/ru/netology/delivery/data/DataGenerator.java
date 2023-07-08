package ru.netology.delivery.data;

import com.github.javafaker.Faker;
import lombok.Value;
import lombok.val;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    private DataGenerator() { //специальный утилитный класс - у него приватный конструктор (в коде не сможем сделать экземпляр класса)
                              //методы утилитного класса вызываются по имени класса (например - DataGenerator.generateCity)
                              //в DataGenerator удобро реализовать небольшие методы, которые будут заниматься генерацией отдельных видов данных
    }

    public static String generateDate(int addDays) { // статичный метод
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String generateCity() { // faker не умеет генерировать набор городов, которые являются только административными центрами
        var cities = new String[] {"Новосибирск", "Москва", "Рязань", "Нижний Новгород", "Красноярск", "Омск"};
        return cities[new Random().nextInt(cities.length)];
    }

    public static String generateName(String locale) {
        var faker = new Faker(new Locale(locale));
        return faker.name().lastName() + " " + faker.name().firstName();
    }

    public static String generatePhone(String locale) {
        var faker = new Faker(new Locale(locale));
        return faker.phoneNumber().phoneNumber();
    }

    public static class Registration {
        private Registration() {
        }

        public static UserInfo generateUser(String locale) {
            return new UserInfo(generateCity(), generateName(locale), generatePhone(locale));
        }
    }

    @Value //рекомендуется аннотировать Value
    public static class UserInfo { //дата класс - специальный класс, который служит для задания структуры объекта с данными, т.е. описываются те поля, которые должны быть в объекте
        private final String city;
        private final String name;
        private final String phone;
    }
}
