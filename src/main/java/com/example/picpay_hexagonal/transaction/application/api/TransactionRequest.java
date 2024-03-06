package com.example.picpay_hexagonal.transaction.application.api;


import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@ToString
public class TransactionRequest {
    BigDecimal amount;
    Long senderId;
    Long receiverId;
}

