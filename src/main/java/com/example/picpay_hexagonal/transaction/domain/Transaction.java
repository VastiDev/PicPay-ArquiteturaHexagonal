package com.example.picpay_hexagonal.transaction.domain;


import com.example.picpay_hexagonal.transaction.application.api.TransactionRequest;
import com.example.picpay_hexagonal.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name= "transactions")
@Table(name="transactions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount;
    @ManyToOne
    @JoinColumn(name="sender_id")
    private User sender;
    @JoinColumn(name="receiver_id")
    @ManyToOne
    private User receiver;
    private LocalDateTime timestamp;

    }


