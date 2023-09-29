package uk.co.roteala.javaprocessor.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import uk.co.roteala.javaprocessor.models.RestaurantTablesResponse;
import uk.co.roteala.javaprocessor.models.Status;
import uk.co.roteala.javaprocessor.models.UpdateTableRequest;
import uk.co.roteala.javaprocessor.models.UnlockTableResponse;
import uk.co.roteala.javaprocessor.models.entity.RestaurantTable;
import uk.co.roteala.javaprocessor.models.repository.RestaurantTableRepository;

import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class RestaurantServices {

    private final RestaurantTableRepository restaurantTableRepository;
    public RestaurantTablesResponse getAllTables() {
        RestaurantTablesResponse response = new RestaurantTablesResponse();

        Page<RestaurantTable> restaurantTablePage = this.restaurantTableRepository
                .findAll(Pageable.ofSize(Integer.MAX_VALUE));

        if(!restaurantTablePage.isEmpty()) {
            response.setRestaurantTables(restaurantTablePage.toList());
            response.setStatus(Status.SUCCESS);
        } else {
            response.setStatus(Status.ERROR);
        }

        return response;
    }

    public UnlockTableResponse unlockTable(@Valid UpdateTableRequest request) {
        UnlockTableResponse response = new UnlockTableResponse();

        if(request.getTableId() != null && request.getTableId() > 0) {
            Optional<RestaurantTable> optionalTable = this.restaurantTableRepository
                    .findById(response.getTableId());

            if(optionalTable.isPresent()) {
                final RestaurantTable table = optionalTable.get();

                //update table
                table.setDetails(request.getDetails());
                this.restaurantTableRepository.save(table);
            }
        }

        return response;
    }
}
