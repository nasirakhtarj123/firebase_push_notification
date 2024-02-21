package com.example.FirebaseInitializer.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.FirebaseInitializer.Service.FirebaseMessagingService;
import com.example.FirebaseInitializer.model.FCMRequest;

@RequestMapping("/api")
@RestController
@Validated

public class FCMController {

    private final FirebaseMessagingService firebaseMessagingService;

    @Autowired
    public FCMController(FirebaseMessagingService firebaseMessagingService) {
        this.firebaseMessagingService = firebaseMessagingService;
    }

    @PostMapping("/sendfcm")
    public ResponseEntity<?> sendFCMMessage(@RequestBody FCMRequest fcmRequest) {
        String errorMessage = validateRequest(fcmRequest);
        if (errorMessage != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }

        return firebaseMessagingService.sendMessage(fcmRequest.getMessage(),
                fcmRequest.getTitle(),
                fcmRequest.getToken());
    }

    private String validateRequest(FCMRequest fcmRequest) {
        if (fcmRequest.getMessage() == null || fcmRequest.getMessage().isEmpty()) {
            return "Message is required";
        }
        if (fcmRequest.getTitle() == null || fcmRequest.getTitle().isEmpty()) {
            return "Title is required";
        }
        if (fcmRequest.getToken() == null || fcmRequest.getToken().isEmpty()) {
            return "Token is required";
        }
        return null;
    }
}
