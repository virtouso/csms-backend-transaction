package com.room.transaction.controller;



import com.room.transaction.dto.DriverInStation.DriverInStationAuthRequest;
import com.room.transaction.dto.MetaResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldAuthenticateDriver() {

        DriverInStationAuthRequest request = new DriverInStationAuthRequest("station_1",new DriverInStationAuthRequest.DriverIdentifier("sdsadas"));
        ResponseEntity<MetaResult> response = restTemplate.postForEntity(
                "/auth/auth_driver",
                request,
                MetaResult.class
        );


        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}