package com.example.mongodbexample;

import com.example.mongodbexample.config.MongoDbConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class MongodbExampleApplication {

    @Autowired
    MongoDbConfig mongoDbConfig;


    //--------------------------------------------------------------------------------------------------------
    @Bean
    public MongoTransactionManager mongoTransactionManager(MongoDatabaseFactory mongoDatabaseFactory){

        return new MongoTransactionManager(mongoDatabaseFactory);
    }
    //--------------------------------------------------------------------------------------------------------

    public static void main(String[] args) {
        SpringApplication.run(MongodbExampleApplication.class, args);
    }


    @EventListener(ApplicationReadyEvent.class)
    public void init(){
        //return "";
        System.out.println("na starcie");
        mongoDbConfig.tryAddElement();
        System.out.println("poooooooo");

    }



}
