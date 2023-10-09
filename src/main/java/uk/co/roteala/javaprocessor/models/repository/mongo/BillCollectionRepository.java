package uk.co.roteala.javaprocessor.models.repository.mongo;

import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import uk.co.roteala.javaprocessor.models.collection.BillCollection;

@Repository
@JaversSpringDataAuditable
public interface BillCollectionRepository extends MongoRepository<BillCollection, String> {
}
