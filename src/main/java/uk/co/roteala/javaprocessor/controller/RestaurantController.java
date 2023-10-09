//package uk.co.roteala.javaprocessor.controller;
//
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.media.Content;
//import io.swagger.v3.oas.annotations.media.Schema;
//import io.swagger.v3.oas.annotations.parameters.RequestBody;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//import uk.co.roteala.javaprocessor.models.*;
//import uk.co.roteala.javaprocessor.models.entity.RestaurantBill;
//import uk.co.roteala.javaprocessor.services.RestaurantServices;
//
//import javax.validation.Valid;
//
//@Slf4j
//@RestController
//@RequestMapping("/restaurant")
//@AllArgsConstructor
//@Tag(name = "Restaurant operations", description = "The API to process restaurant operations")
//public class RestaurantController {
//
//    private final RestaurantServices restaurantServices;
//
//    @Operation(summary = "Get pseudo transaction from the wallet interface, validate it and then send it to the nodes")
//    @RequestBody(content = @Content(mediaType = "application/json", schema = @Schema(implementation = ReceiptGenerationRequest.class)), required = true)
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Transaction validated and added to mempool", content = {@Content(mediaType = "application/json",
//                    schema = @Schema(implementation = ReceiptResponse.class))}),
//            @ApiResponse(responseCode = "404", description = "Invalid transaction data", content = {@Content(mediaType = "application/json",
//                    schema = @Schema(implementation = ApiError.class))}),
//            @ApiResponse(responseCode = "400", description = "BadRequest", content = {@Content(mediaType = "application/json",
//                    schema = @Schema(implementation = ApiError.class))})})
//    @PostMapping("/restaurant-generate-bill")
//    @ResponseStatus(HttpStatus.OK)
//    public ReceiptResponse generateBill(@Valid @org.springframework.web.bind.annotation.RequestBody ReceiptGenerationRequest receiptGenerationRequest) {
//        return this.restaurantServices.generateBill(receiptGenerationRequest);
//    }
//
//    @Operation(summary = "Get pseudo transaction from the wallet interface, validate it and then send it to the nodes")
//    @RequestBody(content = @Content(mediaType = "application/json", schema = @Schema(implementation = BillRequest.class)), required = true)
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Transaction validated and added to mempool", content = {@Content(mediaType = "application/json",
//                    schema = @Schema(implementation = RestaurantBill.class))}),
//            @ApiResponse(responseCode = "404", description = "Invalid transaction data", content = {@Content(mediaType = "application/json",
//                    schema = @Schema(implementation = ApiError.class))}),
//            @ApiResponse(responseCode = "400", description = "BadRequest", content = {@Content(mediaType = "application/json",
//                    schema = @Schema(implementation = ApiError.class))})})
//    @PostMapping("/restaurant-get-bill")
//    @ResponseStatus(HttpStatus.OK)
//    public RestaurantBill generateBill(@Valid @org.springframework.web.bind.annotation.RequestBody BillRequest receiptGenerationRequest) {
//        return this.restaurantServices.getBill(receiptGenerationRequest);
//    }
//}
