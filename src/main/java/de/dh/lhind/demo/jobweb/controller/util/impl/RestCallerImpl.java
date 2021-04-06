package de.dh.lhind.demo.jobweb.controller.util.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.dh.lhind.demo.jobweb.controller.util.AuthenticationFacade;
import de.dh.lhind.demo.jobweb.controller.util.RestCaller;
import de.dh.lhind.demo.jobweb.controller.util.constant.Endpoint;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

import java.util.stream.Collectors;

@Service
@Log4j
public class RestCallerImpl implements RestCaller {

    @Autowired
    private RestOperations restOperations;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @Autowired
    private Endpoint endpoint;

    @Override
    public <T> ResponseEntity<T> getExchange(String url, Class<T> responseType, boolean shouldBeAuth) {
        return exchange(url, HttpMethod.GET, new HttpEntity<>(null, null), responseType, shouldBeAuth);
    }

    @Override
    public <T, K> ResponseEntity<T> postExchange(String url, HttpEntity<K> requestEntity, Class<T> responseType, boolean shouldBeAuth) {
        return exchange(url, HttpMethod.POST, requestEntity, responseType, shouldBeAuth);
    }

    @Override
    public <T> ResponseEntity<T> deleteExchange(String url, Class<T> responseType, boolean shouldBeAuth) {
        return exchange(url, HttpMethod.DELETE, new HttpEntity<>(null, null), responseType, shouldBeAuth);
    }

    @Override
    public <T, K> ResponseEntity<T> putExchange(String url, HttpEntity<K> requestEntity, Class<T> responseType, boolean shouldBeAuth) {
        return exchange(url, HttpMethod.PUT, requestEntity, responseType, shouldBeAuth);
    }

    private <T, K> ResponseEntity<T> exchange(String url, HttpMethod method, HttpEntity<K> requestEntity,
                                              Class<T> responseType, boolean shouldBeAuth) {
        return exchange(url, method, requestEntity, responseType, shouldBeAuth, new Object[0]);
    }

    private <T, K> ResponseEntity<T> exchange(String url, HttpMethod method, HttpEntity<K> requestEntity,
                                              Class<T> responseType, boolean shouldBeAuth, Object... uriVariables) {
        HttpHeaders headers = new HttpHeaders();
        if(shouldBeAuth) {
            headers.set("authorization", "Bearer " + authenticationFacade.getToken());
            headers.addAll(requestEntity.getHeaders());
            log.info("Request headers: ");
            headers.forEach((key, value) -> {
                log.info(String.format(
                        "Header '%s' = %s", key, value.stream().collect(Collectors.joining("|"))));
            });
            log.info("Request body: ");
            printBody(requestEntity.getBody());
        }

        HttpEntity<K> entity = new HttpEntity<>(requestEntity.getBody(), headers);
        String endpointUri = endpoint.getRemoteRootUri() + url;
        ResponseEntity<T> response = restOperations.exchange(endpointUri, method, entity , responseType, uriVariables);

		log.info("Response body: ");
		printBody(response.getBody());

        return response;
    }

    private void printBody(Object object) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        log.info(gson.toJson(object));
    }

}
