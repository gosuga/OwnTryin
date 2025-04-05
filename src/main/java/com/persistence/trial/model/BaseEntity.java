package com.persistence.trial.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.sql.Timestamp;


@Getter
@Setter
@MappedSuperclass
public  abstract  class BaseEntity implements Serializable {

    @Column(name = "created_at",updatable = false)
    @CreationTimestamp
    public Timestamp createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    public Timestamp updatedAt;

}
