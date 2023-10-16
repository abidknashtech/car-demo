package com.elasticsearch.elasticsearch.repository;

import com.elasticsearch.elasticsearch.entity.CarEntity;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarEntityRepository extends ElasticsearchRepository<CarEntity,String> {

    CarEntity findByCarId(Integer carId);
    @Query(value = "?0")
    SearchHits<CarEntity> searchByCustomQuery(String query);
}
