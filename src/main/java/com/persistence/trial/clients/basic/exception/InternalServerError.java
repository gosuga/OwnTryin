package com.persistence.trial.clients.basic.exception;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.persistence.trial.Constants.ErrorConstants;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@ToString(callSuper = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class InternalServerError extends APIException {
    private String reqPath;

    public InternalServerError(String reqPath, String descp) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, ErrorConstants.API_INTERNAL_SERVER_ERROR,descp);
        this.reqPath = reqPath;
    }
}
