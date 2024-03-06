package com.example.picpay_hexagonal.transaction.application.repository;

import com.example.picpay_hexagonal.transaction.domain.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository {
    Transaction save(Transaction transaction);
}
