package com.myclassroom.classroom.config;

//import com.google.cloud.secretmanager.v1.AccessSecretVersionResponse;
//import com.google.cloud.secretmanager.v1.SecretManagerServiceClient;
import com.google.protobuf.ByteString;
import org.postgresql.ds.PGSimpleDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


@Configuration
public class DBConfig {
    Logger logger = LoggerFactory.getLogger(DBConfig.class);
    @Value("${SM_KEY}")
    private String secreteManagerKey;

    @Bean(name = "PgDbConnect")
    @Primary
    public PGSimpleDataSource connectDb(){

//        try{
            // Access Secrets using Secrete Manager Service Client
//            SecretManagerServiceClient secretManagerServiceClient = SecretManagerServiceClient.create();
//            AccessSecretVersionResponse smResponse = secretManagerServiceClient.accessSecretVersion(secreteManagerKey);
//
//            ByteString secretData = smResponse.getPayload().getData();
//            String secretJson = secretData.toStringUtf8();
//            System.out.println(secretJson);
//            // Parse the JSON to get the required properties
////            ObjectMapper objectMapper = new ObjectMapper();
//
//        } catch (Exception e){
//            logger.error("DB Configuration :: Exception :: {}", e.getMessage());
//        }

        System.out.println("==================== DB Connection Begins =================");
        PGSimpleDataSource ds = new PGSimpleDataSource();
        ds.setUrl("jdbc:postgresql://34.93.186.123:5432/classroom?autoReconnect=true&useSSL=false");
        ds.setUser("himanshu");
        ds.setPassword("password@psql");
        System.out.println("==================== DB Connected =================");
        return ds;
    }
}