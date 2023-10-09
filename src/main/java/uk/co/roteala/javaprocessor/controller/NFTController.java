package uk.co.roteala.javaprocessor.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import uk.co.roteala.javaprocessor.models.*;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import uk.co.roteala.javaprocessor.services.BillServices;
import uk.co.roteala.javaprocessor.services.NFTServices;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/bill")
@AllArgsConstructor
@Tag(name = "ETH Transaction Split funding Operations", description = "The API to process the incoming transaction")
public class NFTController {
    private final BillServices billServices;

    @Operation(summary = "Process incoming ETH transaction, splits it into multiple details")
    @RequestBody(content = @Content(mediaType = "application/json", schema = @Schema(implementation = BillRequest.class)), required = true)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transaction processed!", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = BillResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Invalid transaction data", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApiError.class))}),
            @ApiResponse(responseCode = "400", description = "BadRequest", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApiError.class))})})
    @PostMapping("/generate-bill")
    @ResponseStatus(HttpStatus.OK)
    public BillResponse sendTransaction(@Valid @org.springframework.web.bind.annotation.RequestBody BillRequest billRequest){
        return this.billServices.processBill(billRequest);
    }
}
