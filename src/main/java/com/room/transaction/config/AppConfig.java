package com.room.transaction.config;



import com.room.transaction.constants.GeneralConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties
//@Component
public class AppConfig {
    @Value("${env}")
    public  String Env;

    @Value("${kafka.bootstrap-servers}")
    public String bootstrapServers;

    @Value("${kafka.topic.response-topic}")
    public String responseTopic;

    @Value("${kafka.topic.request-topic}")
    public String requestTopic;

    @Value("${kafka.consumergroup}")
    public String consumerGroup;


    public boolean IsProduction(){
        return Env.equals(GeneralConstants.Prod);
    }

}




