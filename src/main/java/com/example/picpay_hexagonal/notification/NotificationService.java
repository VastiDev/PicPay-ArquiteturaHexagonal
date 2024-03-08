package com.example.picpay_hexagonal.notification;

import com.example.picpay_hexagonal.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final RestTemplate restTemplate;

    public void sendNotification(User user, String transactionMessage) {
        String notificationUrl = "https://picpayhexagonal.free.beeceptor.com";
        Map<String, String> notificationRequest = new HashMap<>();
        notificationRequest.put("email", user.getEmail());
        notificationRequest.put("message", transactionMessage);

        try {
            restTemplate.postForObject(notificationUrl, notificationRequest, String.class);
            System.out.println("Notificação enviada para o usuário com email: " + user.getEmail());
        } catch (Exception e) {
            System.err.println("Erro ao enviar notificação para " + user.getEmail() + ": " + e.getMessage());
        }
    }
}
