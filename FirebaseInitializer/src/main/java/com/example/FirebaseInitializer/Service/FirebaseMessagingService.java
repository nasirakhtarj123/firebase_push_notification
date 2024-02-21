package com.example.FirebaseInitializer.Service;

import okhttp3.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import java.io.IOException;

@Service
public class FirebaseMessagingService {

    private static final String FCM_ENDPOINT = "https://fcm.googleapis.com/fcm/send";
    private static final String FCM_SERVER_KEY = "AAAA0_YVrec:APA91bEHmJPFqHhHE1snix8dKfsWABIOrpcmL790SGhDV7SuAyyM6a4oSNI3ciCG2Z3ZapMGaIHJBJrllTxpJ9PXTGmvuodNjqksMwLhLUwMB14b73D9JfeiksKleyNQib09McjjOXSn";

    public ResponseEntity sendMessage(String message, String title, String token) {
        try {
            String json = "{\"notification\": {\"title\": \"" + title + "\",\"body\": \"" + message + "\"},\"to\": \""
                    + token + "\"}";

            OkHttpClient client = new OkHttpClient();

            RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);

            Request request = new Request.Builder()
                    .url(FCM_ENDPOINT)
                    .post(body)
                    .addHeader("Authorization", "key=" + FCM_SERVER_KEY)
                    .addHeader("Content-Type", "application/json")
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    Gson gson = new Gson();
                    // Convert the response body to a JSON object
                    Object jsonObject = gson.fromJson(responseBody, Object.class);
                    // Convert the JSON object to a formatted JSON string
                    String formattedJson = gson.toJson(jsonObject);
                    return ResponseEntity.ok().body(formattedJson);
                } else {
                    return ResponseEntity.status(response.code()).body("Failed to send message to FCM server");
                }
            }
        } catch (IOException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
