package com.example.picpay_hexagonal.user.domain;

import com.example.picpay_hexagonal.user.application.api.UserRequest;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name= "users")
@Table(name="users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class User {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String document;
    @Column(unique = true)
    private String email;
    private String password;
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private UserType userType;
    private LocalDateTime localDateTime;

    public User(UserRequest userRequest) {
        this.firstName = userRequest.getFirstName();
        this.lastName = userRequest.getLastName();
        this.document = userRequest.getDocument();
        this.email = userRequest.getEmail();
        this.password = userRequest.getPassword();
        this.balance = userRequest.getBalance();
        this.userType = userRequest.getUserType();
    }
        public boolean canSendMoney(BigDecimal amount) {
            return this.userType != UserType.MERCHANT && this.balance.compareTo(amount) >= 0;
        }
}


