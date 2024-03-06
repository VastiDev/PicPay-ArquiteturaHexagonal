package com.example.picpay_hexagonal.transaction.application.api;

import com.example.picpay_hexagonal.transaction.application.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TransactionController implements TransactionAPI {
    private final TransactionService transactionService;

    @Override
    public TransactionResponse createTransacion(TransactionRequest transactionRequest) throws Exception {
        TransactionResponse transactionCreated = transactionService.createTransaction(transactionRequest);
        return transactionCreated;
    }
}
