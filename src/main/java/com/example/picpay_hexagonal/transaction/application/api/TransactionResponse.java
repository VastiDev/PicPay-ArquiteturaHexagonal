package com.example.picpay_hexagonal.transaction.application.api;

import com.example.picpay_hexagonal.transaction.domain.Transaction;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class TransactionResponse {
    private Long id;
    public TransactionResponse(Transaction transaction) {
        this.id = transaction.getId();
    }
}
