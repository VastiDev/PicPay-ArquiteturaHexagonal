package com.example.picpay_hexagonal.transaction.application.infra;

import com.example.picpay_hexagonal.transaction.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionSpringDataJpaRepository extends JpaRepository<Transaction, Long> {
}
