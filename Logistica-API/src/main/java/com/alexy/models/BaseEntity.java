////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2020                                                          /
// developed by Abdelilah Dehaoui GitHub : Abdelilah00                         /
////////////////////////////////////////////////////////////////////////////////

package com.alexy.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

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

    /*@CreationTimestamp
    private LocalDateTime createdAt;*/
    private LocalDateTime createdAt = LocalDateTime.ofInstant(between().toInstant(), ZoneId.systemDefault());
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt = null;

    private long createdBy;
    private long updatedBy;
    private long deletedBy;

    public static Date between() {
        LocalDateTime endExclusive = LocalDateTime.now();
        LocalDateTime startInclusive = endExclusive.minusYears(21);

        long startMillis = startInclusive.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long endMillis = endExclusive.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long randomMillisSinceEpoch = ThreadLocalRandom
                .current()
                .nextLong(startMillis, endMillis);
        return new Date(randomMillisSinceEpoch);
    }
}

