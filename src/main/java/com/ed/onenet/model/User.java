package com.ed.onenet.model;

import com.ed.onenet.model.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "user")
@Entity
public class User extends BaseEntity {

    @Column
    private String username;

    @Column
    private String email;


}
