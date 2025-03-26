package com.room.transaction.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record MetaResult(
        @JsonProperty("errors") List<String> errors,
        @JsonProperty("developer_message") String developerMessage,
        @JsonProperty("message_code") String messageCode) {




}






