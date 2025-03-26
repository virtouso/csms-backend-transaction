package com.room.transaction.util;


import com.room.transaction.config.AppConfig;
import com.room.transaction.dto.MetaResult;
import com.room.transaction.dto.MetaResultTyped;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MetaResultFactory {

    final AppConfig appConfig;

    public MetaResultFactory(AppConfig appConfig) {
        this.appConfig = appConfig;
    }


    public MetaResult create(String developerMessage, String messageCode, List<String> errors) {
        String devMessage = appConfig.IsProduction() ? "" : developerMessage;
        return new MetaResult(errors,devMessage, messageCode);
    }

    public  <T> MetaResultTyped<T> create(T result, String developerMessage, String messageCode, List<String> errors) {
        String devMessage = appConfig.IsProduction() ? "" : developerMessage;
        return new MetaResultTyped<>(result,errors, devMessage, messageCode);
    }


}
