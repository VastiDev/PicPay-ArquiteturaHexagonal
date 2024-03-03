package com.example.picpay_hexagonal.user.application.api;

import com.example.picpay_hexagonal.user.domain.UserType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
@Getter
@ToString
public class UserRequest {
    private String firstName;
    private String lastName;

    private String document;

    private String email;
    private String password;
    private BigDecimal balance;

    private UserType userType;
}
