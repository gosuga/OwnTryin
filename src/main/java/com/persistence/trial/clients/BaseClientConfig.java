package com.persistence.trial.clients;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class BaseClientConfig {
    private String baseUrl;
    private Integer connectionTimeoutInMillis;
    private long readTimeoutInMillis;
    private long writeTimeoutInMillis;
    private long responseTimeoutInMillis;
    private long maxRetries;
    private long retryBackOff;
    private Map<String, String> endpoints;


}
