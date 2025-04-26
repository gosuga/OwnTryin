package com.persistence.trial.clients.basic.exception;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.persistence.trial.Constants.ErrorConstants;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@ToString(callSuper = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class MaxRetriesExceededException extends APIException{


    public MaxRetriesExceededException( String description) {
        super(HttpStatus.SERVICE_UNAVAILABLE,
                ErrorConstants.MAX_RETRIES_EXCEEDED, description);
    }
}
