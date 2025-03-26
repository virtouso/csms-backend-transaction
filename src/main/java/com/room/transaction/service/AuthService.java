package com.room.transaction.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.room.transaction.config.AppConfig;
import com.room.transaction.dto.DriverInStation.DriverInStationAuthRequest;
import com.room.transaction.dto.DriverInStation.DriverStationAuthResponse;
import com.room.transaction.dto.MetaResultTyped;
import com.room.transaction.util.MetaResultFactory;
import jakarta.validation.Valid;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;



import java.util.UUID;
import java.util.concurrent.ExecutionException;


@Service
public class AuthService {

    final ReplyingKafkaTemplate<String, String, String> kafkaTemplate;
    final MetaResultFactory metaResultFactory;
    final AppConfig appConfig;
    ObjectMapper objectMapper = new ObjectMapper();

    public AuthService(ReplyingKafkaTemplate<String, String, String> kafkaTemplate, MetaResultFactory metaResultFactory, AppConfig appConfig) {
        this.kafkaTemplate = kafkaTemplate;
        this.metaResultFactory = metaResultFactory;
        this.appConfig = appConfig;
    }

    public MetaResultTyped<DriverStationAuthResponse> AuthDriver( DriverInStationAuthRequest request) throws ExecutionException, InterruptedException, JsonProcessingException {


        var str = objectMapper.writeValueAsString(request);

        ProducerRecord<String, String> record = new ProducerRecord<String, String>(appConfig.requestTopic, str);

        record.headers().add(new RecordHeader(KafkaHeaders.REPLY_TOPIC, appConfig.responseTopic.getBytes()));
        RequestReplyFuture<String, String, String> sendAndReceive = kafkaTemplate.sendAndReceive(record);


        SendResult<String, String> sendResult = sendAndReceive.getSendFuture().get();

        ConsumerRecord<String, String> consumerRecord = sendAndReceive.get();
        // return consumer value
        var response = consumerRecord.value();
        var modeled = objectMapper.readValue(response, DriverStationAuthResponse.class);
        return metaResultFactory.create(modeled, "response from kafka other microservice", "ok",  null);

    }


}
