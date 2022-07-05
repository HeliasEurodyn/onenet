package com.ed.onenet.service;

import com.ed.onenet.dto.DataOfferingDTO;
import com.ed.onenet.mapper.DataOfferingMapper;
import com.ed.onenet.model.DataOffering;
import com.ed.onenet.repository.DataOfferingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DataOfferingService {

    private final DataOfferingRepository dataOfferingRepository;

    @Autowired
//    private DataOfferingMapper dataOfferingMapper;

    public DataOfferingService(DataOfferingRepository dataOfferingRepository) {
        this.dataOfferingRepository = dataOfferingRepository;
    }

    public DataOfferingDTO getObject(String id) {
        DataOffering entity = this.dataOfferingRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Entity does not exist")
        );
//        return this.dataOfferingMapper.map(entity);
        return null;
    }

    public List<DataOfferingDTO> getObject() {
        List<DataOffering> list = this.dataOfferingRepository.findAll();
//        return this.dataOfferingMapper.map(list);
        return null;
    }

}
