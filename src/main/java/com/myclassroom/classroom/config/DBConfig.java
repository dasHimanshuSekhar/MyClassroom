package com.myclassroom.classroom.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.secretmanager.v1.AccessSecretVersionResponse;
import com.google.cloud.secretmanager.v1.SecretManagerServiceClient;
import com.google.cloud.secretmanager.v1.SecretManagerServiceSettings;
import com.google.protobuf.ByteString;
import org.postgresql.ds.PGSimpleDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import static com.myclassroom.classroom.utils.GeneralConstants.*;


@Configuration
public class DBConfig {
    Logger logger = LoggerFactory.getLogger(DBConfig.class);
    @Value("${SM_KEY}")
    private String secreteManagerKey;

    @Bean(name = "PgDbConnect")
    @Primary
    public PGSimpleDataSource connectDb(){
        Map<String, String> secretProperties = new HashMap<>();
        PGSimpleDataSource ds = new PGSimpleDataSource();
        try{
            // Access Secrets using Secrete Manager Service Client
            SecretManagerServiceClient secretManagerServiceClient = SecretManagerServiceClient.create(SecretManagerServiceSettings.newBuilder()
                    .setCredentialsProvider(() -> GoogleCredentials.
                            fromStream(new FileInputStream("src/main/resources/my-classroom-443609-49259e886c7b.json")))
                    .build());
            AccessSecretVersionResponse smResponse = secretManagerServiceClient.accessSecretVersion(secreteManagerKey);

            ByteString secretData = smResponse.getPayload().getData();
            String secretJson = secretData.toStringUtf8();
            ObjectMapper objectMapper = new ObjectMapper();
            secretProperties = objectMapper.readValue(secretJson, new TypeReference<Map<String, String>>() {});
            System.out.println(secretJson);
            System.out.println("==================== DB Connection Begins =================");
            ds.setUrl(secretProperties.get(JDBC_URL));
            ds.setUser(secretProperties.get(DB_USER));
            ds.setPassword(secretProperties.get(DB_PASS));

            // System Properties Set
            System.setProperty(DRIVER_CLASS_NAME, secretProperties.get("spring.datasource.driver-class-name"));
            System.setProperty(JPA_DIALECT, secretProperties.get("spring.jpa.properties.hibernate.dialect"));
            System.setProperty(JPA_DDL_AUTO, secretProperties.get("spring.jpa.hibernate.ddl-auto"));

            System.out.println("==================== DB Connected =================");

            // System Properties Clear
            System.clearProperty(DRIVER_CLASS_NAME);
            System.clearProperty(JPA_DIALECT);
            System.clearProperty(JPA_DDL_AUTO);

            secretManagerServiceClient.shutdown();
        } catch (Exception e){
            e.printStackTrace();
            logger.error("DB Configuration :: Exception :: {}", e.getMessage());
        }
        return ds;
    }
}