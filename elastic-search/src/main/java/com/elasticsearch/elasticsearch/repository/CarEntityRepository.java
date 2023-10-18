package com.elasticsearch.elasticsearch.repository;

import com.elasticsearch.elasticsearch.entity.CarEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarEntityRepository extends ElasticsearchRepository<CarEntity, String> {


    CarEntity findByCarId(Integer carId);

    CarEntity findByBrand(String brand);

    CarEntity findByMileage(Double mileage);

    CarEntity findByPrice(Double price);
}
