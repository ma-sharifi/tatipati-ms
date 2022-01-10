package com.tatipati.file.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.annotations.SerializedName;
import com.tatipati.file.dto.VideoQualityAttribute;
import com.tatipati.file.util.serializer.GSONModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VideoAttributeDto extends GSONModel {
    @SerializedName("quality")
    private VideoQualityAttribute quality;
    @SerializedName("duration_ms")
    private Long durationMS;
    @SerializedName("duration_string")
    private String durationString;
}
