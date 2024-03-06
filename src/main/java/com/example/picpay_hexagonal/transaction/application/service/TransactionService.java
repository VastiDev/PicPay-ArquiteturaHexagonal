package com.example.picpay_hexagonal.transaction.application.service;

import com.example.picpay_hexagonal.transaction.application.api.TransactionRequest;
import com.example.picpay_hexagonal.transaction.application.api.TransactionResponse;
import org.springframework.stereotype.Service;

@Service
public interface TransactionService {
    TransactionResponse createTransaction(TransactionRequest transactionRequest) throws Exception;
}
