package com.ed.onenet.model;

import com.ed.onenet.dto.UserDTO;
import com.ed.onenet.model.common.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;
import java.time.Instant;

@Data
@Accessors(chain = true)
@Table(name = "data_catalog_data_offerings")
@Entity
public class DataOffering extends BaseEntity {

//    @CreatedBy
//    @Column(name = "created_by", updatable = false)
//    private String createdBy;

    @Column(name = "created_on", nullable = false)
    private Instant createdOn;

    @Column(name = "modified_by", updatable = false)
    private String modifiedBy;

    @Column(name = "modified_on", nullable = false)
    private Instant modifiedOn;

    @Column(name = "input_profile", length = 500)
    private String inputProfile;

    @Column(name = "input_data_source", length = 500)
    private String inputDataSource;

    @Lob
    @Column(name = "comments")
    private String comments;

    @Column(name = "data_catalog_business_object_id", length = 36)
    private String dataCatalogBusinessObjectId;

    @ManyToOne(fetch = FetchType.LAZY,
            targetEntity = com.ed.onenet.model.User.class)
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    User user = new User();

}
