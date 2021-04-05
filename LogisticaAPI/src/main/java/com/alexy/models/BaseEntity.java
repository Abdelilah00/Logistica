////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2020                                                          /
// developed by Abdelilah Dehaoui GitHub : Abdelilah00                         /
////////////////////////////////////////////////////////////////////////////////

package com.alexy.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.*;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@MappedSuperclass
@FilterDef(
        name = "tenantFilter",
        parameters = @ParamDef(name = "tenantId", type = "long")
)
@Filter(
        name = "tenantFilter",
        condition = "tenantId = :tenantId"
)

@FilterDef(
        name = "deleteFilter"
)
@Filter(
        name = "deleteFilter",
        condition = "deletedAt is null"
)
public abstract class BaseEntity extends IdEntity {
    private long tenantId;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt = null;

    private long createdBy;
    private long updatedBy;
    private long deletedBy;
}

