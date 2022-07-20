package com.ed.onenet.controller;

import com.ed.onenet.service.EntityService;
import com.ed.onenet.service.IdsResourceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/ids-resource")
@Slf4j
public class IdsResourceController {
    private final IdsResourceService idsResourceService;

    public IdsResourceController(IdsResourceService idsResourceService) {
        this.idsResourceService = idsResourceService;
    }

    @GetMapping
    public String getTest(){
        return "{\"title\":\"hello\"}";
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,  consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> postObjectData(@RequestParam("id") String formId,
                                              @RequestBody Map<String, Map<String, Object>> parameters,
                                              @RequestHeader Map<String, String> headers) {

        return this.idsResourceService.postObject(formId, parameters, headers);
    }

    @GetMapping(path = "/by-provider")
    public   Map<String, Map<String, Object>> getFromProvider(@RequestParam("id") String id,
                                               @RequestHeader Map<String, String> headers) {
        return this.idsResourceService.getFromProvider(id, headers);
    }

}
