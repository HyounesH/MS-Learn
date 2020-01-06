package org.yh.learn.carservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yh.learn.communservice.domain.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
}
