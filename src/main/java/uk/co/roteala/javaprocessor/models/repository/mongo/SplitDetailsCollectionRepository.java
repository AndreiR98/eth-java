package uk.co.roteala.javaprocessor.models.repository.mongo;

import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import uk.co.roteala.javaprocessor.models.collection.SplitDetailsCollections;

@Repository
@JaversSpringDataAuditable
public interface SplitDetailsCollectionRepository extends MongoRepository<SplitDetailsCollections, String> {
}
