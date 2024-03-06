package com.example.picpay_hexagonal.transaction.application.service;

import com.example.picpay_hexagonal.handler.APIException;
import com.example.picpay_hexagonal.transaction.application.api.TransactionRequest;
import com.example.picpay_hexagonal.transaction.application.api.TransactionResponse;
import com.example.picpay_hexagonal.transaction.application.repository.TransactionRepository;
import com.example.picpay_hexagonal.transaction.domain.Transaction;
import com.example.picpay_hexagonal.user.application.repository.UserRepository;
import com.example.picpay_hexagonal.user.application.service.UserApplicationService;
import com.example.picpay_hexagonal.user.application.service.UserService;
import com.example.picpay_hexagonal.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Log4j2
@Service
@RequiredArgsConstructor
public class TransactionApplicationService implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final UserApplicationService userService;

    @Override
    public TransactionResponse createTransaction(TransactionRequest transactionRequest) throws Exception {
        User sender = userRepository.getUserById(transactionRequest.getSenderId());
        User receiver = userRepository.getUserById(transactionRequest.getReceiverId());

        validateAndAuthorizeTransaction(sender, receiver, transactionRequest.getAmount());

        Transaction transaction = createAndSaveTransaction(sender, receiver, transactionRequest.getAmount());

        Transaction savedTransaction = transactionRepository.save(transaction);
        log.info("transação salva:{}", transaction);

        userService.updateBalances(sender, receiver, transaction.getAmount());;

        return new TransactionResponse(savedTransaction);
    }

    private Transaction createAndSaveTransaction(User sender, User receiver,
                                                 BigDecimal amount) {
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setSender(sender);
        transaction.setReceiver(receiver);
        transaction.setTimestamp(LocalDateTime.now());
        return transactionRepository.save(transaction);
    }

    private void validateAndAuthorizeTransaction(User sender, User receiver, BigDecimal amount) throws Exception {
        userService.validateTransaction(sender, amount);
        if (!sender.canSendMoney(amount)) {
            throw APIException.build(HttpStatus.BAD_REQUEST, "Transaçao não autorizada!");
        }
    }
}
