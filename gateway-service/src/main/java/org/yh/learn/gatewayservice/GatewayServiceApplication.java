package org.yh.learn.gatewayservice;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yh.learn.communservice.domain.Car;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@EnableFeignClients
@EnableCircuitBreaker
@EnableDiscoveryClient
@EnableZuulProxy
@SpringBootApplication
public class GatewayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServiceApplication.class, args);
    }

    @FeignClient("car-service")
    interface CarClient {

        @GetMapping("/cars")
        @CrossOrigin
        List<Car> getCars();
    }

    @RestController
    class CoolCarController {
        private final CarClient carClient;

        public CoolCarController(CarClient carClient) {
            this.carClient = carClient;
        }

        private List<Car> fallback() {
            return new ArrayList<>();
        }
         @GetMapping("/famous-cars")
         @CrossOrigin
         @HystrixCommand(fallbackMethod = "fallback")
        public List<Car> goodCars() {
            return carClient.getCars();
        }
    }

}
