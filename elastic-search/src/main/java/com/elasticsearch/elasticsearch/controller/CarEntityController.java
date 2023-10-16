package com.elasticsearch.elasticsearch.controller;

import com.elasticsearch.elasticsearch.entity.CarEntity;
import com.elasticsearch.elasticsearch.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/apis/car")
public class CarEntityController {
    @Autowired
    private CarService service;
    /*@Autowired
    private AzureKafkaProducer producer;*/

    @GetMapping("/all")
    public ResponseEntity<List<CarEntity>> getAllCarEntity() {

        return new ResponseEntity<>(service.getAllCarEntity(), HttpStatus.OK);
    }

    @GetMapping("/{carId}")
    public ResponseEntity<CarEntity> getCarDetailsById(@PathVariable("carId") String carId) throws IOException {
        CarEntity carEntityWithCarId = service.getCarEntityWithCarId(Integer.valueOf(carId));
        return new ResponseEntity<>(carEntityWithCarId, HttpStatus.OK);
    }

    @PostMapping("/save")
    public CarEntity saveCarEntity(@RequestBody CarEntity carEntity) {
       // producer.send(carEntity);
        return service.saveCarEntity(carEntity);
    }
}
