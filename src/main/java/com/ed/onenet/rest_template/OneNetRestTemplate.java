package com.ed.onenet.rest_template;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Map;

@Service
public class OneNetRestTemplate {

    @Value("${orion.consumer.ip}")
    private String onenetConsumerEndpoint;

    @Value("${orion.provider.ip}")
    private String onenetProviderEndpoint;

    @Value("${sofia.uri}")
    private String sofiaUri;

    private final RestTemplate restTemplate;

    public OneNetRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Map<String, Object> update(Map<String, Object> jsonLdParameters,
                                              Map<String, String> headers, String id) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/ld+json");
        HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<Map<String, Object>>(jsonLdParameters, httpHeaders);

        ResponseEntity<Object> response =
        restTemplate.exchange(
                URI.create(onenetProviderEndpoint + "/ngsi-ld/v1/entities/urn:ngsi-ld:" + id + "/attrs"),
                HttpMethod.POST,
                httpEntity,
                new ParameterizedTypeReference<Object>() {
                }
        );

        return jsonLdParameters;
    }

    public void saveOnenetResponse(Map<String, Map<String, Object>> parameters,
                                                  String id,
                                      Map<String, String> headers) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");
        httpHeaders.add("Authorization", headers.get("authorization"));
        HttpEntity<Map<String, Map<String, Object>>> httpEntity =
                new HttpEntity<Map<String, Map<String, Object>>>(parameters, httpHeaders);

        restTemplate.postForObject(
                URI.create(sofiaUri + "/form?id=" + id ),
                httpEntity,
                Void.class
        );

    }

    public Map<String, Object> post(Map<String, Object> jsonLdParameters,
                                            Map<String, String> headers) {

        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/ld+json");
        HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<Map<String, Object>>(jsonLdParameters, httpHeaders);
        try {
            restTemplate.exchange(
                    URI.create(onenetProviderEndpoint +"/ngsi-ld/v1/entities/"),
                    HttpMethod.POST,
                    httpEntity,
                    void.class
            );
        }catch (Exception ex){
            String s = "";
            s = ex.getMessage();

        }



        return jsonLdParameters;
    }

    public Boolean checkExistance(String id) {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            HttpEntity httpEntity = new HttpEntity(httpHeaders);
            restTemplate.exchange(onenetProviderEndpoint + "/ngsi-ld/v1/entities/urn:ngsi-ld:" + id,
                    HttpMethod.GET,
                    httpEntity,
                    new ParameterizedTypeReference<Object>() {
                    }
            );
        } catch (HttpStatusCodeException ex) {
            return false;
        }
        return true;
    }

    public Map<String, Object> getFromProvider(String id) {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            HttpEntity httpEntity = new HttpEntity(httpHeaders);
            ResponseEntity<Map<String, Object>> responce =
                    restTemplate.exchange(onenetProviderEndpoint + "/ngsi-ld/v1/entities/urn:ngsi-ld:" + id,
                    HttpMethod.GET,
                    httpEntity,
                    new ParameterizedTypeReference<Map<String, Object>>() {
                    }
            );
            return responce.getBody();
        } catch (HttpStatusCodeException ex) {
            return null;
        }
    }

    public Map<String, Object> sourceRegistration(Map<String, Object> jsonLdParameters) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/ld+json");
        HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<Map<String, Object>>(jsonLdParameters, httpHeaders);

        restTemplate.postForObject(
                URI.create(onenetProviderEndpoint + "/ngsi-ld/v1/csourceRegistrations/"),
                httpEntity,
                Void.class
        );

        return jsonLdParameters;
    }

}
