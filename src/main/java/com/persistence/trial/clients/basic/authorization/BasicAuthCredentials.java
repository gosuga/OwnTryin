package com.persistence.trial.clients.basic.authorization;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BasicAuthCredentials {
    private String userName;
    private String passWord;

}
