package com.logistica.dtos.Dashboard.Predictions;

import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemOfPredSeries {
    private Date time;
    private Double value = 0d;
}

