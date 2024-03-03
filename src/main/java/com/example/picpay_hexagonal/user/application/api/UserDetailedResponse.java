package com.example.picpay_hexagonal.user.application.api;

import com.example.picpay_hexagonal.user.domain.User;
import com.example.picpay_hexagonal.user.domain.UserType;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Getter
@ToString
public class UserDetailedResponse {
    private String firstName;
    private String lastName;
    private String document;
    private String email;
    private String password;
    private BigDecimal balance;
    private UserType userType;
    private LocalDateTime localDateTime;

    public UserDetailedResponse(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.document = user.getDocument();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.balance = user.getBalance();
        this.userType = user.getUserType();
        this.localDateTime = LocalDateTime.now();
    }

}
