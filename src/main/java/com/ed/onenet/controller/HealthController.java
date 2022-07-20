package com.ed.onenet.controller;

import com.ed.onenet.service.IdsResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/health")
@Slf4j
public class HealthController {

    @GetMapping
    public String getObject(){
        return "{\"status\":\"ok\"}";
    }

}
