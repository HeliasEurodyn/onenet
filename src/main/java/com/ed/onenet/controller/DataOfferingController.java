package com.ed.onenet.controller;

import com.ed.onenet.dto.DataOfferingDTO;
import com.ed.onenet.service.DataOfferingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/data-offering")
@Slf4j
public class DataOfferingController {
    private final DataOfferingService dataOfferingService;

    public DataOfferingController(DataOfferingService dataOfferingService) {
        this.dataOfferingService = dataOfferingService;
    }

    @GetMapping
    public List<DataOfferingDTO> getObjectById() {
        return this.dataOfferingService.getObject();
    }

    @GetMapping(value = "/{id}")
    public DataOfferingDTO getObjectById(@PathVariable("id") String id) {
        return this.dataOfferingService.getObject(id);
    }


}
