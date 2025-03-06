package org.rexapi.services;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import java.io.FileInputStream;
import java.io.IOException;

@ApplicationScoped
public class FirebaseService {

    @PostConstruct
    void init() {
        try {
            FileInputStream serviceAccount = new FileInputStream("src/main/resources/rexproject-94523-firebase-adminsdk-fbsvc-34216194cd.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error inicializando Firebase: " + e.getMessage());
        }
    }

    public void sendNotification(String token, String title, String body) {
        try {
            Message message = Message.builder()
                    .setToken(token)
                    .putData("title", title)
                    .putData("body", body)
                    .build();

            String response = FirebaseMessaging.getInstance().send(message);
            System.out.println("Notificación enviada con éxito: " + response);

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error al enviar la notificación: " + e.getMessage());
        }
    }
}
