package uk.co.roteala.javaprocessor.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import uk.co.roteala.javaprocessor.models.TransactionRequest;
import uk.co.roteala.javaprocessor.models.TransactionResponse;

import javax.validation.Valid;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class NFTServices {
    public TransactionResponse processTransaction(@Valid TransactionRequest request) {
        return null;
    }
}
