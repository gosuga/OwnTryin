package com.persistence.trial.user;

import com.persistence.trial.Constants.Constants;
import com.persistence.trial.clients.BaseClient;
import com.persistence.trial.clients.BaseClientConfig;
import com.persistence.trial.user.config.UserServiceClientConfig;
import com.persistence.trial.user.response.UserDetailResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j

public class UserServiceClient extends BaseClient {

    @Autowired
    public UserServiceClient(UserServiceClientConfig userServiceClientConfig) {
        super(userServiceClientConfig);
    }

    public UserDetailResponse getCustosmerDeteils(String customerKey) {

        String customerData = getBaseClientConfig().getEndpoints().get(Constants.GET_CUSTOMER_DETAILS);
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put(Constants.GET_CUSTOMER_DETAILS, customerData);
        try{
            UserDetailResponse usr= (UserDetailResponse)



        }catch (Exception ex){

        }



    }


}



