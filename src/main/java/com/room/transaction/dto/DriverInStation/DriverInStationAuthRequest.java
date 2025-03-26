package com.room.transaction.dto.DriverInStation;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DriverInStationAuthRequest(
        @JsonProperty("stationUuid")
        @NotBlank(message = "Station UUID cannot be blank")
        String stationUuid,

        @JsonProperty("driverIdentifier")
        @NotNull(message = "Driver identifier cannot be null")
        DriverIdentifier driverIdentifier
) {

    public record DriverIdentifier(
            @JsonProperty("id")
            @NotBlank(message = "Driver ID cannot be blank")
            String id
    ) {
    }
}
