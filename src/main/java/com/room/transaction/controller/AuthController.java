package com.room.transaction.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.room.transaction.dto.DriverInStation.DriverInStationAuthRequest;
import com.room.transaction.dto.DriverInStation.DriverStationAuthResponse;
import com.room.transaction.dto.MetaResultTyped;
import com.room.transaction.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/auth")
public class AuthController {


    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/auth_driver")
    public ResponseEntity<MetaResultTyped<DriverStationAuthResponse>> AuthDriver(@Valid @RequestBody DriverInStationAuthRequest request) throws ExecutionException, InterruptedException, JsonProcessingException {

        var result= authService.AuthDriver(request);

        return new ResponseEntity<MetaResultTyped<DriverStationAuthResponse>>(result, HttpStatus.ACCEPTED);

    }
}



