package com.elasticsearch.elasticsearch.service.impl;

import com.elasticsearch.elasticsearch.entity.CarEntity;
import com.elasticsearch.elasticsearch.repository.CarEntityRepository;
import com.elasticsearch.elasticsearch.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Profile("azure")
public class AzureCarService implements CarService {
    @Autowired
    private CarEntityRepository repository;

    @Override
    public CarEntity getCarEntityWithCarId(Integer carId) {
        CarEntity byCarId = repository.findByCarId(carId);
        return byCarId;
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

