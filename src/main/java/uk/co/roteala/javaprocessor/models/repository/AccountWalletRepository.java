package uk.co.roteala.javaprocessor.models.repository;

import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import uk.co.roteala.javaprocessor.models.entity.AccountWallet;

import java.util.Optional;

@Repository
@JaversSpringDataAuditable
public interface AccountWalletRepository extends PagingAndSortingRepository<AccountWallet, Integer> {
    @Override
    Optional<AccountWallet> findById(Integer integer);

    Optional<AccountWallet> findByUniqueMerchantCode(String uniqueMerchantCode);
}
