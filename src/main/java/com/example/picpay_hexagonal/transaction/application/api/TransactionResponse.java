package com.example.picpay_hexagonal.transaction.application.api;

import com.example.picpay_hexagonal.transaction.domain.Transaction;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class TransactionResponse {
    private Long id;
    private String statusMessage;

    public TransactionResponse(Transaction transaction, String statusMessage) {
        this.id = transaction.getId();
        this.statusMessage = statusMessage;
    }

    public TransactionResponse(Transaction savedTransaction) {
        this.id = savedTransaction.getId();
        this.statusMessage = "Transação concluída com sucesso!";
    }
}

