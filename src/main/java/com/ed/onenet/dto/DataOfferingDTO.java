package com.ed.onenet.dto;

import com.ed.onenet.dto.common.BaseDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class DataOfferingDTO extends BaseDTO {

    private String inputProfile;

    private String inputDataSource;

    private String comments;

    private String dataCatalogBusinessObjectId;

    UserDTO user = new UserDTO();

}
