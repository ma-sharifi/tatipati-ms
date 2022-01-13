package com.tatipati.file.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
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
    @JsonProperty("image_height")
    private int imageHeight;
    @JsonProperty("image_width")
    private int imageWidth;
    @SerializedName("duration_ms")
    @JsonProperty("duration_ms")
    private Long durationMS;
    @SerializedName("duration_string")
    @JsonProperty("duration_string")
    private String durationString;
    @JsonProperty("audio_bitrate")
    private int audioBitrate;//125588 OR 2116800
}
