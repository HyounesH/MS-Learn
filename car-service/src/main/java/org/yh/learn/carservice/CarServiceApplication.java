package org.yh.learn.carservice;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.yh.learn.communservice.domain.Car;
import org.yh.learn.communservice.repositories.CarRepository;

import java.util.stream.Stream;

@SpringBootApplication
@EnableDiscoveryClient
public class CarServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarServiceApplication.class, args);
    }

    @Bean
    ApplicationRunner init(CarRepository carRepository) {
        return args -> {
            Stream.of("Ferrari", "Jaguar", "Porsche", "Lamborghini", "Bugatti", "Pagani", "Tesla", "Rolls-Royce", "Rolls-Royce Holdings", "McLaren Automative", "Mercedes-Benz").forEach(name -> {
                carRepository.save(new Car(name));
            });
            carRepository.findAll().forEach(System.out::println);
        };
    }
}
