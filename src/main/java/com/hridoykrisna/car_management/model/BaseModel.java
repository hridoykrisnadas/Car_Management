package com.hridoykrisna.car_management.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@MappedSuperclass
@Data
@ToString
public abstract class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(updatable = false)
    private int createdBy;
    @Column(updatable = false)
    private Date createdAt;
    private int updateBy;
    private Date updateAt;
    private Boolean isActive;

    @PrePersist
    public void setPreInsertData() {
        this.createdAt = new Date();
        this.isActive = true;
    }

    @PreUpdate
    public void setPostUpdateData() {
        this.updateAt = new Date();
    }
}