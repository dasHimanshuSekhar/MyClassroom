package com.myclassroom.classroom.config;
import com.google.api.client.util.Value;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseConfig {
    Logger logger = LoggerFactory.getLogger(FirebaseConfig.class);

    @Bean
    public Firestore firestore() throws IOException {
        String fireBaseKey = "src/main/resources/firebase_key.json";
        FileInputStream serviceAccount = new FileInputStream(fireBaseKey);

            FirebaseOptions firebaseOptions = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();
            FirebaseApp.initializeApp(firebaseOptions);
            logger.info("firebase_config :: Successfully Connected");
            return FirestoreClient.getFirestore();
    }
}