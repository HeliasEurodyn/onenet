package com.ed.onenet.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Column;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class UserDTO {

    private String id;

    @NotNull
    private String username;

    @NotNull
    private String email;

    private String companyId;

    private String companyName;

    private String companyAddress;

    private String phoneNumber;

}
