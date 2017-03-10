/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solidbeans.core.async.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.Netty4ClientHttpRequestFactory;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 *
 * @author magnus.wahlstrom@solidbeans.com
 */
public class AsyncRestClient {

    private final String baseUrl;
    private final AsyncRestTemplate asyncRestTemplate;
    
    protected AsyncRestClient(String baseUrl) {
        this.baseUrl = baseUrl;
        this.asyncRestTemplate = new AsyncRestTemplate(new Netty4ClientHttpRequestFactory());
    }

    protected <O> CompletableFuture<O> get(String url, Class<O> response) {
        return get(url, null, response);
    }
    
    protected <O> CompletableFuture<O> get(String url, Map<String, String> headers, Class<O> response) {        
        return execute(exchange(url, HttpMethod.GET, headers, null, response));
    }

    protected <I, O> CompletableFuture<O> post(String url, I entity, Class<O> response) {                     
        return post(url, null, entity, response);
    }

    protected <I, O> CompletableFuture<O> post(String url, Map<String, String> headers, I entity, Class<O> response) {
        return execute(exchange(url, HttpMethod.POST, headers, entity, response));
    }

    protected <I, O> CompletableFuture<O> put(String url, I entity, Class<O> response) {                     
        return put(url, null, entity, response);
    }

    protected <I, O> CompletableFuture<O> put(String url, Map<String, String> headers, I entity, Class<O> response) {
        return execute(exchange(url, HttpMethod.PUT, headers, entity, response));
    }

    protected CompletableFuture<Void> delete(String url) {
        return delete(url, null);
    }
    
    protected CompletableFuture<Void> delete(String url, Map<String, String> headers) {        
        return execute(exchange(url, HttpMethod.DELETE, headers, null, Void.class));
    }

    private <I, O> ListenableFuture<ResponseEntity<O>> exchange(String url, HttpMethod httpMethod, Map<String, String> headers, I entity, Class<O> response) {
        HttpEntity<I> httpEntity = new HttpEntity<>(entity);

        if(headers != null) {
            headers.forEach((header, value) -> httpEntity.getHeaders().add(header, value));
        }                
        
        return asyncRestTemplate.exchange(baseUrl + url, httpMethod, httpEntity, response);
    }
    
    private <T> CompletableFuture<T> execute(ListenableFuture<ResponseEntity<T>> responseListener) {
        CompletableFuture<T> completableFuture = new CompletableFuture<>();        
        
        responseListener.addCallback((successResult) -> completableFuture.complete(successResult.getBody()),
                completableFuture::completeExceptionally);
        
        return completableFuture;
    }
}
