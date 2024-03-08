package com.example.picpay_hexagonal.authorization;

import com.example.picpay_hexagonal.handler.APIException;
import com.example.picpay_hexagonal.transaction.application.api.TransactionRequest;
import com.example.picpay_hexagonal.transaction.application.api.TransactionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class AuthorizationService {
    private final RestTemplate restTemplate;


    public AuthorizationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean authorizeTransaction(TransactionRequest transactionRequest) {
        String url = "https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc";
     try{
         String rawResponse = restTemplate.getForObject(url, String.class);
         System.out.println(rawResponse); // Loga a resposta bruta

         Map<String, Object> response = restTemplate.getForObject(url, Map.class);
         String message = (String) response.get("message");
         return "Autorizado".equalsIgnoreCase(message);
     }catch (RestClientResponseException e) {

         System.err.println("Erro ao autorizar a transação: " + e.getMessage());
         System.err.println("Status code: " + e.getRawStatusCode());
         System.err.println("Response body: " + e.getResponseBodyAsString());

         return false;
     }
    }
}
