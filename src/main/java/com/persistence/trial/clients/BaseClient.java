package com.persistence.trial.clients;

import com.persistence.trial.Constants.Constants;
import com.persistence.trial.Constants.ErrorConstants;
import com.persistence.trial.clients.basic.authorization.BasicAuthCredentials;
import com.persistence.trial.clients.basic.exception.ClientError;
import com.persistence.trial.clients.basic.exception.InternalServerError;
import com.persistence.trial.clients.basic.exception.MaxRetriesExceededException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriBuilder;
import reactor.netty.http.client.HttpClient;
import reactor.util.retry.Retry;
import reactor.util.retry.RetryBackoffSpec;

import java.net.URI;
import java.time.Duration;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

import static java.util.Locale.filter;

@Getter
@Slf4j
public class BaseClient {
    //  private  WebClient webClient;
    private WebClient webClient;
    private BaseClientConfig baseClientConfig;

    public BaseClient(BaseClientConfig baseClientConfig1) {
        this.baseClientConfig = baseClientConfig1;
        webClient = createClient();
    }

    public WebClient createClient() {
        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(HttpClient.create()))
                .filter((request, next) -> {
                    Map<String, String> objMap = MDC.getCopyOfContextMap();
                    return next.exchange(request)
                            .doOnNext(value -> {
                                if (!Objects.isNull(objMap)) {
                                    MDC.setContextMap(objMap);
                                }
                            });
                })
                .build();
    }

    public Object get(String path, Map<String, Object> pathParams, Map<String, Object> queryParams, Map<String, String> headers,
                      BasicAuthCredentials basicAuthCredentials, Class<?> responseModel) throws Throwable {
        long start, end, lantency;
        start = System.currentTimeMillis();
        try {
            Object response = webClient.get()
                    .uri(creatURI(path, pathParams, queryParams))
                    .headers(HttpHeaders -> addHeaders(HttpHeaders, headers))
                    .headers(HttpHeaders -> addAuthentication(basicAuthCredentials, HttpHeaders))
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .onStatus(status -> status.is4xxClientError(),
                            responses -> responses.createException().map(
                                    ex -> new ClientError(path,
                                            HttpStatus.resolve(responses.statusCode().value()),
                                            ex.getResponseBodyAsString())))
                    .onStatus(status-> status.is5xxServerError(),
                            respons -> respons.createException().map(
                                    ex -> new InternalServerError(path, ex.getResponseBodyAsString())))
                    .bodyToMono(responseModel)
                    .retryWhen(getRetryConfiguration())
                    .block();
            end=System.currentTimeMillis();
            lantency=end-start;
            log.info("response time",lantency,"Get", baseClientConfig.getBaseUrl(),creatURI(path,queryParams,pathParams) );
            JsonUtil
        } catch (Exception ex) {

        }

    }

    private void addAuthentication(BasicAuthCredentials authCredentials, HttpHeaders httpHeaders) {
        if (!Objects.isNull(httpHeaders)) {
            httpHeaders.setBasicAuth(authCredentials.getUserName(),
                    authCredentials.getPassWord());
        }

    }

    private URI creatURI(String path, Map<String, Object> pathParam, Map<String, Object> queryParams) {
        DefaultUriBuilderFactory defaultUriBuilderFactory = new DefaultUriBuilderFactory();
        UriBuilder uriBuilder = defaultUriBuilderFactory.builder().path(path);
        if (!Objects.isNull(queryParams) && !queryParams.isEmpty()) {
            queryParams.forEach(uriBuilder::queryParam);
        }
        return (!Objects.isNull(queryParams) && !pathParam.isEmpty()) ?
                uriBuilder.build(pathParam) : uriBuilder.build();
    }

    private void addHeaders(HttpHeaders httpHeaders, Map<String, String> headers) {
        if (!Objects.isNull(headers) && !headers.isEmpty()) {
            headers.forEach(httpHeaders::add);
        }
        httpHeaders.add(Constants.L_FROM_SERVICE, Constants.OrderService);

        //  Consumer<HttpHeaders> objheaders = heaad -> heaad.add(Constants.L_FROM_SERVICE, Constants.OrderService);
        // objheaders.accept(httpHeaders);

    }

    public Retry getRetryConfiguration(){
        return  Retry.backoff(baseClientConfig.getMaxRetries(),
                Duration.ofSeconds(baseClientConfig.getRetryBackOff()))
                .filter(throwable -> ( throwable instanceof ClientError))
                .doBeforeRetry(retry ->
                     log.error(ErrorConstants.RETRYING_API_CALL+ retry.failure()))
                .onRetryExhaustedThrow((retryBackoffSpec,retrySignal) -> new MaxRetriesExceededException( retrySignal.failure().toString()));

    }
}
