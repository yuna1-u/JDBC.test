package org.peaksoft;

import org.peaksoft.model.Car;
import org.peaksoft.model.User;
import org.peaksoft.service.CarServiceImpl;
import org.peaksoft.service.UserServiceImpl;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // TODO: 27.09.2023   реализуйте алгоритм здесь

        LocalDate localDate = LocalDate.of(2024, 4, 1);
        UserServiceImpl userService = new UserServiceImpl();
        CarServiceImpl carService = new CarServiceImpl();

        userService.createTable();
        userService.save(new User("Rabi", "Buzurmanalieva", (byte) 17, (long) 123456));
        userService.save(new User("Sami", "Uraimjanova", (byte) 16, (long) 654321));

        carService.createTable();
        carService.save(new Car("Car", localDate, "white"));
        carService.save(new Car("car2", localDate, "black"));
    }
}
