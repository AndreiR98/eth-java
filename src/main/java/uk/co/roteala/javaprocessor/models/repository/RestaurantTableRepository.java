package uk.co.roteala.javaprocessor.models.repository;

import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import uk.co.roteala.javaprocessor.models.entity.AccountWallet;
import uk.co.roteala.javaprocessor.models.entity.RestaurantTable;

import java.util.Optional;

@Repository
@JaversSpringDataAuditable
public interface RestaurantTableRepository extends PagingAndSortingRepository<RestaurantTable, Integer> {
}
