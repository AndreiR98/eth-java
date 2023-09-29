package uk.co.roteala.javaprocessor.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import uk.co.roteala.javaprocessor.models.*;
import uk.co.roteala.javaprocessor.services.RestaurantServices;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/restaurant")
@AllArgsConstructor
@Tag(name = "Restaurant operations", description = "The API to process restaurant operations")
public class RestaurantController {

    private final RestaurantServices restaurantServices;

    @Operation(summary = "Retrieve restaurant tables")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transaction processed!", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = TransactionResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Invalid transaction data", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApiError.class))}),
            @ApiResponse(responseCode = "400", description = "BadRequest", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApiError.class))})
    })
    @GetMapping("/restaurant-tables")
    @ResponseStatus(HttpStatus.OK)
    public RestaurantTablesResponse getAllRestaurantTables() {
        return this.restaurantServices.getAllTables();
    }

    @Operation(summary = "Process incoming ETH transaction, splits it into multiple details")
    @RequestBody(content = @Content(mediaType = "application/json", schema = @Schema(implementation = UpdateTableRequest.class)), required = true)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transaction processed!", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = UnlockTableResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Invalid transaction data", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApiError.class))}),
            @ApiResponse(responseCode = "400", description = "BadRequest", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApiError.class))})})
    @PostMapping("/unlock-table")
    @ResponseStatus(HttpStatus.OK)
    public UnlockTableResponse unlockTable(@Valid @org.springframework.web.bind.annotation.RequestBody UpdateTableRequest unlockTableRequest) {
        return this.restaurantServices.unlockTable(unlockTableRequest);
    }
}
