package com.ed.onenet.controller;

import com.ed.onenet.service.EntityService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/entity")
@Slf4j
public class EntityController {
    private final EntityService entityService;

    public EntityController(EntityService entityService) {
        this.entityService = entityService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,  consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> postObjectData(@RequestParam("id") String formId,
                                              @RequestBody Map<String, Map<String, Object>> parameters,
                                              @RequestHeader Map<String, String> headers) throws JsonProcessingException {

        return this.entityService.postObject(formId, parameters, headers);
    }

    @GetMapping(path = "/by-provider")
    public   Map<String, Map<String, Object>> getFromProvider(@RequestParam("id") String id,
                                               @RequestHeader Map<String, String> headers) {
        return this.entityService.getFromProvider(id, headers);
    }

}
