package com.example.picpay_hexagonal.user.application.service;

import com.example.picpay_hexagonal.handler.APIException;
import com.example.picpay_hexagonal.transaction.application.repository.TransactionRepository;
import com.example.picpay_hexagonal.transaction.domain.Transaction;
import com.example.picpay_hexagonal.user.application.api.UserDetailedResponse;
import com.example.picpay_hexagonal.user.application.api.UserListResponse;
import com.example.picpay_hexagonal.user.application.api.UserRequest;
import com.example.picpay_hexagonal.user.application.api.UserResponse;
import com.example.picpay_hexagonal.user.application.repository.UserRepository;
import com.example.picpay_hexagonal.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.example.picpay_hexagonal.user.domain.UserType;

import java.math.BigDecimal;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserApplicationService implements UserService {
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;

    @Override
    public UserResponse criaUser(UserRequest userRequest) {
        User user = userRepository.salva(new User(userRequest));
        return new UserResponse(user);
    }

    @Override
    public UserDetailedResponse getUserById(Long id)  {
        User user = userRepository.getUserById(id);
        return new UserDetailedResponse(user);
    }

    @Override
    public List<UserListResponse> getAllUsers() {
        List<User> users = userRepository.getAllUsers();
        return UserListResponse.converte(users) ;
    }
    public void validateTransaction(User sender, BigDecimal amount)  {
        if(sender.getUserType() == UserType.MERCHANT){
            throw APIException.build(HttpStatus.BAD_REQUEST,"Usuário do tipo Lojista não está autorizado a realizar transação!");
        }
        if(sender.getBalance().compareTo(amount) < 0){
            throw APIException.build(HttpStatus.BAD_REQUEST,"Saldo insuficiente para realizar a transação!");
        }
    }

    public void updateBalances(User sender, User receiver, BigDecimal amount) {
        sender.setBalance(sender.getBalance().subtract(amount));
        receiver.setBalance(receiver.getBalance().add(amount));
        updateUser(sender);
        updateUser(receiver);
    }
    public void updateUser(User user) {
        userRepository.salva(user);
    }
}
