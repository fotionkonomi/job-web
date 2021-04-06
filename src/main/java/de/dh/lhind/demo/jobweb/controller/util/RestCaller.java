package de.dh.lhind.demo.jobweb.controller.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

public interface RestCaller {
    <T> ResponseEntity<T> getExchange(String url,
                                      Class<T> responseType, boolean shouldBeAuth);

    <T, K> ResponseEntity<T> postExchange(String url, HttpEntity<K> requestEntity,
                                          Class<T> responseType, boolean shouldBeAuth);

    <T> ResponseEntity<T> deleteExchange(String url,
                                      Class<T> responseType, boolean shouldBeAuth);

    <T, K> ResponseEntity<T> putExchange(String url, HttpEntity<K> requestEntity,
                                          Class<T> responseType, boolean shouldBeAuth);
}
