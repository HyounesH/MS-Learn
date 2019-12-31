package org.yh.learn.communservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.yh.learn.communservice.domain.Car;

@RepositoryRestResource
public interface CarRepository extends JpaRepository<Car, Long> {
    
}
