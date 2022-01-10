package com.tatipati.file.dto;

import com.tatipati.file.util.serializer.GSONModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.Duration;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LimitDto extends GSONModel {
    private Integer remaining;
    private Integer limit;
    private Long timeWindow;//Seconds
    private String description;

    public LimitDto(Integer remaining, Integer limit) {
        this.remaining = remaining;
        this.limit = limit;
        timeWindow= Duration.ofHours(24).getSeconds();
        description="تعداد " +limit+ " فایل از " + limit + " فایل شما باقی مانده است";
    }
}
