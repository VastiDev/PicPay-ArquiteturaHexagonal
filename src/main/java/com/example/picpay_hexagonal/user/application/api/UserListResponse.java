package com.example.picpay_hexagonal.user.application.api;

import com.example.picpay_hexagonal.user.domain.User;
import com.example.picpay_hexagonal.user.domain.UserType;
import lombok.Getter;
import lombok.ToString;
import lombok.Value;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Value
public class UserListResponse {
    private String firstName;
    private String lastName;
    private String document;
    private String email;
    private BigDecimal balance;
    private UserType userType;


    public static List<UserListResponse> converte(List<User> users) {
        return users.stream()
            .map(UserListResponse::new).collect(Collectors.toList());
    }

    public UserListResponse(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.document = user.getDocument();
        this.email = user.getEmail();
        this.balance = user.getBalance();
        this.userType = user.getUserType();
    }
}


