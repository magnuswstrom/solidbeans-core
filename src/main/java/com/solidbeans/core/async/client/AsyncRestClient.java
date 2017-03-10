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

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Async rest client that uses async rest template together with Netty as provider
 *
 * @author magnus.wahlstrom@solidbeans.com
 */
public class AsyncRestClient {

    private final String baseUrl;
    private final AsyncRestTemplate asyncRestTemplate;

    /**
     * Creates an async rest client
     *
     * @param baseUrl Base URL for all rest calls from this client
     */
    protected AsyncRestClient(String baseUrl) {
        checkNotNull(baseUrl, "Base URL is null");

        this.baseUrl = baseUrl;
        this.asyncRestTemplate = new AsyncRestTemplate(new Netty4ClientHttpRequestFactory());
    }

    /**
     * HTTP GET
     *
     * @param url Relative URL to resource
     * @param response Response class type
     * @param <O> Response type
     * @return Completable future reference
     */
    protected <O> CompletableFuture<O> get(String url, Class<O> response) {
        return get(url, null, response);
    }

    /**
     * HTTP GET
     *
     * @param url Relative URL to resource
     * @param headers HTTP headers to add in request
     * @param response Response class type
     * @param <O> Response type
     * @return Completable future reference
     */
    protected <O> CompletableFuture<O> get(String url, Map<String, String> headers, Class<O> response) {
        checkNotNull(url, "URL is null");
        checkNotNull(response, "Response class is null");

        return execute(exchange(url, HttpMethod.GET, headers, null, response));
    }

    /**
     * HTTP POST
     *
     * @param url Relative URL to resource
     * @param entity Input entity that will be sent as payload
     * @param response Response class type
     * @param <O> Response type
     * @return Completable future reference
     */
    protected <I, O> CompletableFuture<O> post(String url, I entity, Class<O> response) {
        return post(url, null, entity, response);
    }

    /**
     * HTTP POST
     *
     * @param url Relative URL to resource
     * @param headers HTTP headers to add in request
     * @param entity Input entity that will be sent as payload
     * @param response Response class type
     * @param <O> Response type
     * @return Completable future reference
     */
    protected <I, O> CompletableFuture<O> post(String url, Map<String, String> headers, I entity, Class<O> response) {
        checkNotNull(url, "URL is null");
        checkNotNull(response, "Response class is null");

        return execute(exchange(url, HttpMethod.POST, headers, entity, response));
    }

    /**
     * HTTP PUT
     *
     * @param url Relative URL to resource
     * @param entity Input entity that will be sent as payload
     * @param response Response class type
     * @param <O> Response type
     * @return Completable future reference
     */
    protected <I, O> CompletableFuture<O> put(String url, I entity, Class<O> response) {
        return put(url, null, entity, response);
    }

    /**
     * HTTP PUT
     *
     * @param url Relative URL to resource
     * @param headers HTTP headers to add in request
     * @param entity Input entity that will be sent as payload
     * @param response Response class type
     * @param <O> Response type
     * @return Completable future reference
     */
    protected <I, O> CompletableFuture<O> put(String url, Map<String, String> headers, I entity, Class<O> response) {
        checkNotNull(url, "URL is null");
        checkNotNull(response, "Response class is null");

        return execute(exchange(url, HttpMethod.PUT, headers, entity, response));
    }

    /**
     * HTTP DELETE
     *
     * @param url Relative URL to resource
     * @return Completable future reference
     */
    protected CompletableFuture<Void> delete(String url) {
        return delete(url, null);
    }

    /**
     * HTTP DELETE
     *
     * @param url Relative URL to resource
     * @param headers HTTP headers to add in request
     * @return Completable future reference
     */
    protected CompletableFuture<Void> delete(String url, Map<String, String> headers) {
        checkNotNull(url, "URL is null");

        return execute(exchange(url, HttpMethod.DELETE, headers, null, Void.class));
    }

    /**
     * Make exchange using async rest template
     *
     * @param url Relative URL to resource
     * @param httpMethod HTTP method
     * @param headers HTTP headers to add in request
     * @param entity Input entity that will be sent as payload
     * @param response Response class type
     * @param <I> <input type
     * @param <O> Response type
     * @return Callback that will be invoked when HTTP request is finished
     */
    private <I, O> ListenableFuture<ResponseEntity<O>> exchange(String url, HttpMethod httpMethod, Map<String, String> headers, I entity, Class<O> response) {
        HttpEntity<I> httpEntity = new HttpEntity<>(entity);

        if(headers != null) {
            headers.forEach((header, value) -> httpEntity.getHeaders().add(header, value));
        }                
        
        return asyncRestTemplate.exchange(baseUrl + url, httpMethod, httpEntity, response);
    }

    /**
     * Executes completable future functionality
     *
     * @param responseListener Callback that will be invoked when HTTP request is finished
     * @param <T> Output type
     * @return Completable future reference
     */
    private <T> CompletableFuture<T> execute(ListenableFuture<ResponseEntity<T>> responseListener) {
        CompletableFuture<T> completableFuture = new CompletableFuture<>();        
        
        responseListener.addCallback((successResult) -> completableFuture.complete(successResult.getBody()),
                completableFuture::completeExceptionally);
        
        return completableFuture;
    }
}
