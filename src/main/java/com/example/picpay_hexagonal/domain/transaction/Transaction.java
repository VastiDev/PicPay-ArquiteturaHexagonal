package com.example.picpay_hexagonal.domain.transaction;

import com.example.picpay_hexagonal.domain.user.Users;
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
    private Users sender;
    @JoinColumn(name="receiver_id")
    @ManyToOne
    private Users receiver;
    private LocalDateTime timestamp;
}

