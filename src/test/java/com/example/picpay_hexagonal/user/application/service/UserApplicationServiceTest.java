package com.example.picpay_hexagonal.user.application.service;

import com.example.picpay_hexagonal.handler.APIException;
import com.example.picpay_hexagonal.transaction.application.repository.TransactionRepository;
import com.example.picpay_hexagonal.user.application.api.UserDetailedResponse;
import com.example.picpay_hexagonal.user.application.api.UserListResponse;
import com.example.picpay_hexagonal.user.application.api.UserRequest;
import com.example.picpay_hexagonal.user.application.api.UserResponse;
import com.example.picpay_hexagonal.user.application.repository.UserRepository;
import com.example.picpay_hexagonal.user.domain.User;
import com.example.picpay_hexagonal.user.domain.UserType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserApplicationServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private UserApplicationService userApplicationService;

    @Test
    void criaUser_Success() {
        UserRequest userRequest = new UserRequest(/* Dummy data for user request */);
        User user = new User(userRequest);
        when(userRepository.salva(any(User.class))).thenReturn(user);

        UserResponse result = userApplicationService.criaUser(userRequest);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(user.getId());
    }

    @Test
    void getUserById_Success() {
        Long userId = 1L;
        User user = new User(/* Dummy data for user */);
        given(userRepository.getUserById(userId)).willReturn(user);

        UserDetailedResponse result = userApplicationService.getUserById(userId);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(user.getId());
    }

    @Test
    void getAllUsers_Success() {
        List<User> users = Arrays.asList(new User(/* Dummy user 1 */), new User(/* Dummy user 2 */));
        given(userRepository.getAllUsers()).willReturn(users);

        List<UserListResponse> result = userApplicationService.getAllUsers();

        assertThat(result).isNotEmpty();
        assertThat(result.size()).isEqualTo(users.size());
    }

    @Test
    void validateTransaction_Success() {
        // Create a User with a UserType that is not MERCHANT and with a positive balance
        User sender = new User(/* other necessary attributes */);
        sender.setUserType(UserType.COMMON); // Assuming UserType.CUSTOMER is valid and not MERCHANT
        sender.setBalance(new BigDecimal("100.00")); // Set a balance greater than the amount to be validated

        BigDecimal amount = new BigDecimal("50.00"); // Amount less than the balance

        // No exception expected
        userApplicationService.validateTransaction(sender, amount);
    }

    @Test
    void validateTransaction_ThrowsExceptionForMerchant() {
        User sender = new User(/* initialize with necessary attributes */);
        sender.setUserType(UserType.MERCHANT); // Set user as merchant
        sender.setBalance(new BigDecimal("1000")); // Ensure balance is not null

        // Expect APIException for UserType.MERCHANT
        assertThatThrownBy(() -> userApplicationService.validateTransaction(sender, new BigDecimal("100")))
                .isInstanceOf(APIException.class)
                .hasMessageContaining("Usuário do tipo Lojista não está autorizado a realizar transação!");
    }

    @Test
    void validateTransaction_ThrowsExceptionForInsufficientBalance() {
        User sender = new User(/* initialize with necessary attributes */);
        sender.setUserType(UserType.COMMON); // Set user as non-merchant
        sender.setBalance(new BigDecimal("50")); // Set an insufficient balance

        // Expect APIException for insufficient balance
        assertThatThrownBy(() -> userApplicationService.validateTransaction(sender, new BigDecimal("100")))
                .isInstanceOf(APIException.class)
                .hasMessageContaining("Saldo insuficiente para realizar a transação!");
    }


    @Test
    void updateBalances_Success() {
        // Assuming the User class has a constructor that sets the balance or a setter method for balance.
        User sender = new User(/* other necessary attributes */);
        sender.setBalance(new BigDecimal("200")); // Ensure the sender has enough balance

        User receiver = new User(/* other necessary attributes */);
        receiver.setBalance(new BigDecimal("100")); // Initial balance for the receiver

        BigDecimal amount = new BigDecimal("100");

        userApplicationService.updateBalances(sender, receiver, amount);

        assertThat(sender.getBalance()).isEqualByComparingTo("100"); // sender's balance after subtracting amount
        assertThat(receiver.getBalance()).isEqualByComparingTo("200"); // receiver's balance after adding amount
    }

}


