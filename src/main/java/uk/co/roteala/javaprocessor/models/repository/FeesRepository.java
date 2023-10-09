package uk.co.roteala.javaprocessor.models.repository;

import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import uk.co.roteala.javaprocessor.models.entity.Fees;
import uk.co.roteala.javaprocessor.models.entity.RestaurantBill;

import java.util.Optional;

@Repository
@JaversSpringDataAuditable
public interface FeesRepository extends PagingAndSortingRepository<Fees, Integer> {

    Optional<Fees> findByFeesId(Integer feesId);
}
