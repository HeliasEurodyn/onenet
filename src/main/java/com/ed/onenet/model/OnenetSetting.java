package com.ed.onenet.model;

import javax.persistence.*;
import java.time.Instant;

@Table(name = "onenet_settings")
@Entity
public class OnenetSetting {
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

    @Column(name = "orion_consumer_ip")
    private String orionConsumerIp;

    @Column(name = "orion_provider_ip")
    private String orionProviderIp;

    @Column(name = "company_name", length = 500)
    private String companyName;

    @Column(name = "company_address", length = 500)
    private String companyAddress;

    @Column(name = "phone_number", length = 30)
    private String phoneNumber;

    @Column(name = "company_id", length = 36)
    private String companyId;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getOrionProviderIp() {
        return orionProviderIp;
    }

    public void setOrionProviderIp(String orionProviderIp) {
        this.orionProviderIp = orionProviderIp;
    }

    public String getOrionConsumerIp() {
        return orionConsumerIp;
    }

    public void setOrionConsumerIp(String orionConsumerIp) {
        this.orionConsumerIp = orionConsumerIp;
    }

    public Instant getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Instant modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
