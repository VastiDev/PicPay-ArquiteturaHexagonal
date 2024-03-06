package com.example.picpay_hexagonal.transaction.application.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public interface TransactionAPI {

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    TransactionResponse createTransacion (@RequestBody TransactionRequest transactionRequest) throws Exception;
}
