package uk.co.roteala.javaprocessor.models.repository;

import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import uk.co.roteala.javaprocessor.models.entity.RestaurantBill;
import uk.co.roteala.javaprocessor.models.entity.RestaurantTable;

import java.util.Optional;

@Repository
@JaversSpringDataAuditable
public interface RestaurantBillsRespository extends PagingAndSortingRepository<RestaurantBill, Integer> {
    Optional<RestaurantBill> findByTableId(Integer tableId);
}
