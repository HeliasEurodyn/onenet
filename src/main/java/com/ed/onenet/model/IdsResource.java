package com.ed.onenet.model;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;

@Data
@Table(name = "ids_resources")
@Entity
public class IdsResource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "created_by", length = 36)
    private String createdBy;

    @Column(name = "created_on")
    private Instant createdOn;

    @Column(name = "modified_by", length = 36)
    private String modifiedBy;

    @Column(name = "modified_on")
    private Instant modifiedOn;

    @Column(name = "title", length = 200)
    private String title;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "publisher", length = 200)
    private String publisher;

    @Column(name = "keywords", length = 200)
    private String keywords;

    @Column(name = "standard_license", length = 200)
    private String standardLicense;

    @Column(name = "ids_version", length = 200)
    private String idsVersion;

    @Column(name = "language", length = 4)
    private String language;

    @Column(name = "policy", length = 200)
    private String policy;

    @Column(name = "interval_policy_from")
    private Instant intervalPolicyFrom;

    @Column(name = "interval_policy_to")
    private Instant intervalPolicyTo;

    @Column(name = "source_type", length = 200)
    private String sourceType;
}
