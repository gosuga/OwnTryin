package com.persistence.trial.clients.basic.exception;

import com.persistence.trial.Constants.ErrorConstants;
import org.springframework.http.HttpStatus;

public class ClientError extends APIException {
    private  String reqPath;

    public  ClientError(String mesage, HttpStatus httpStatus,String descp){
        super(httpStatus,descp, ErrorConstants.CLIENT_ERROR);
        this.reqPath=mesage;
    }
}
