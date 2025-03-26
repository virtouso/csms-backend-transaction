package com.room.transaction.dto.DriverInStation;

import com.fasterxml.jackson.annotation.JsonProperty;


public record DriverStationAuthResponse(
        @JsonProperty("authorizationStatus") AuthorizationStatus authorizationStatus
) {


    public enum AuthorizationStatus {
        ACCEPTED("Accepted"),
        INVALID("Invalid"),
        UNKNOWN("Unknown"),
        REJECTED("Rejected");

        private final String value;

        AuthorizationStatus(String value) {
            this.value = value;
        }

        public static AuthorizationStatus fromString(String value) {
            for (AuthorizationStatus status : AuthorizationStatus.values()) {
                if (status.value.equalsIgnoreCase(value)) {
                    return status;
                }
            }
            throw new IllegalArgumentException("Unknown authorization status: " + value);
        }
    }

}