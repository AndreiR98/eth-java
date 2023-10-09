//package uk.co.roteala.javaprocessor.services;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.bouncycastle.jce.provider.BouncyCastleProvider;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//import org.springframework.validation.annotation.Validated;
//import uk.co.roteala.javaprocessor.models.*;
//import uk.co.roteala.javaprocessor.models.entity.RestaurantBill;
//import uk.co.roteala.javaprocessor.models.entity.RestaurantTable;
//import uk.co.roteala.javaprocessor.models.repository.RestaurantBillsRespository;
//import uk.co.roteala.javaprocessor.models.repository.RestaurantTableRepository;
//
//import javax.validation.Valid;
//import java.nio.charset.StandardCharsets;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.security.Security;
//import java.time.Instant;
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Objects;
//import java.util.Optional;
//
//@Slf4j
//@Service
//@Validated
//@RequiredArgsConstructor
//public class RestaurantServices {
//
//    private final RestaurantTableRepository restaurantTableRepository;
//    private final RestaurantBillsRespository restaurantBillsRespository;
//
//    public RestaurantBill getBill(@Valid BillRequest request) {
//        RestaurantBill restaurantBill = null;
//
//        Optional<RestaurantBill> restaurantBillOptional = this.
//                restaurantBillsRespository.findByTableId(request.getTableId());
//
//        if(restaurantBillOptional.isPresent()) {
//            restaurantBill = restaurantBillOptional.get();
//        }
//
//        return restaurantBill;
//    }
//
//    public ReceiptResponse generateBill(@Valid ReceiptGenerationRequest request) {
//        ReceiptResponse response = new ReceiptResponse();
//
//        //Get the table
//        Optional<RestaurantTable> optionalTable = this.restaurantTableRepository
//                .findById(request.getTableId());
//        if(optionalTable.isEmpty()) {
//            log.error("No table for id:{}", request.getTableId());
//        }
//
//        final RestaurantTable table = optionalTable.get();
//
//        if(table.getBillId() == null) {
//            RestaurantBill bill = new RestaurantBill();
//            bill.setWaiterId(request.getWaiterId());
//            bill.setTableId(request.getTableId());
//            bill.setSplitNumbers(0);
//            bill.setPayedStatus(false);
//            bill.setSplitType("N");
//            bill.setTableNumber(table.getTableNumber());
//            bill.setTipAmount(request.getTip());
//            bill.setTaxAmount(request.getTax());
//            bill.setTotalAmount(request.getTotal());
//            bill.setDetails(table.getDetails());
//            bill.setProcessDate(LocalDate.now());
//            bill.setTimeStamp(request.getTimeStamp());
//            log.info("bill:{}",bill);
//
//            bill.setUniqueRefId(bytesToHexString(computeSHA3(jsonConverter(bill))));
//
//            final Integer billId = this.restaurantBillsRespository.save(bill).getId();
//
//            table.setBillId(billId);
//            this.restaurantTableRepository.save(table);
//
//            response.setBill(bill);
//            response.setStatus(Status.SUCCESS);
//        }
//
//
//
//        return response;
//    }
//
//    private String jsonConverter(Object details) {
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.registerModule(new JavaTimeModule());
//
//        try {
//            return mapper.writeValueAsString(details);
//        } catch (Exception e) {
//            log.error("Error:{}", e);
//            return null;
//        }
//    }
//
//    public byte[] computeSHA3(String input) {
//        try {
//            Security.addProvider(new BouncyCastleProvider());
//            MessageDigest crypt = MessageDigest.getInstance("SHA-256");
//
//            crypt.update(input.getBytes(StandardCharsets.UTF_8));
//
//            return crypt.digest();
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public String bytesToHexString(byte[] bytes) {
//        StringBuilder hexString = new StringBuilder();
//        for (byte b : bytes) {
//            String hex = String.format("%02x", b);
//            hexString.append(hex);
//        }
//        return hexString.toString();
//    }
//}
