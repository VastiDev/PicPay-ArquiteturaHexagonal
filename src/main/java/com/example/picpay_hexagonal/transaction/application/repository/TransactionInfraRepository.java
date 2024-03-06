package com.example.picpay_hexagonal.transaction.application.repository;

import com.example.picpay_hexagonal.transaction.application.infra.TransactionSpringDataJpaRepository;
import com.example.picpay_hexagonal.transaction.domain.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TransactionInfraRepository implements TransactionRepository {
    private final TransactionSpringDataJpaRepository transactionSpringDataJpaRepository;

    @Override
    public Transaction save(Transaction transaction) {
        transactionSpringDataJpaRepository.save(transaction);
        return transaction;
    }
}
