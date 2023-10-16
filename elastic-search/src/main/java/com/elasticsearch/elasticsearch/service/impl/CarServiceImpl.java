package com.elasticsearch.elasticsearch.service.impl;

import com.elasticsearch.elasticsearch.entity.CarEntity;
import com.elasticsearch.elasticsearch.repository.CarEntityRepository;
import com.elasticsearch.elasticsearch.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarEntityRepository repository;

    @Override
    public CarEntity getCarEntityWithCarId(Integer carId) {
        return repository.findByCarId(carId);
    }

    @Override
    public CarEntity getCarEntityWithBrandName(String brand) {
        return repository.findByBrand(brand);
    }

    @Override
    public CarEntity getCarEntityWithCarPrice(Double price) {
        return repository.findByPrice(price);
    }

    @Override
    public CarEntity getCarEntityWithCarMileage(Double mileage) {
        return repository.findByMileage(mileage);
    }

    @Override
    public List<CarEntity> getAllCarEntity() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public CarEntity saveCarEntity(CarEntity entity) {
        return repository.save(entity);
    }
}

