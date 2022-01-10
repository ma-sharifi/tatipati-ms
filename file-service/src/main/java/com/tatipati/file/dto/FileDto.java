package com.tatipati.file.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import com.tatipati.file.util.serializer.GSONModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

/**
 * @author Mahdi Sharifi
 * @version 1.0.0
 * https://www.linkedin.com/in/mahdisharifi/
 * @since ۵/۲۹/۲۰۲۰
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FileDto extends RepresentationModel<FileDto> {
    @SerializedName("id")
    private Long id;//http
    private String url;//http
//    private String downloadUrl;//http
    @SerializedName("download_uuid_url")
    @JsonProperty("download_uuid_url")
    private String downloadUuidUrl;//http
    private String play;//http
    private String title;
    @JsonProperty("file_limit")
    @SerializedName("file_limit")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LimitDto fileLimit;
    private String contentType;
    private String mimeType;
    private String bucket;
    private long price;
    private String realName;
    private List<CategoryDto> categories ;
    private String extension;
    @SerializedName("size_byte")
    private Long sizeByte;
    @SerializedName("size_string")
    private String sizeString;
    private String type;
    private String mobileNo;
    private String postUuid;
    private Long postId;
    private String token;
private boolean isFree;
    private String uuid;

    @SerializedName("created_at")
    private String createdAt;
    private String createdAtString;

    @SerializedName("on_bottom")
    private String onBottom;
    @SerializedName("on_top")
    private String onTop;

    private String quality;

    private String qualitySize;
    private String qualityDuration;
    @SerializedName("duration_ms")
    private Long durationMS;
    @SerializedName("duration_string")
    private String durationString;
    @JsonProperty("video_attribute")
    private VideoAttributeDto videoAttribute=new VideoAttributeDto();

    @SerializedName("channel")
    @JsonProperty("channel")
    private VideoChannelDto channelDto=new VideoChannelDto();

    private Integer height;
    private Integer width;

    private Integer minAge;
    private Integer maxAge;
    private String ageRange;
    private Boolean enabled;
    private Boolean isPublic;





    public FileDto(Long id) {
        this.id = id;
    }

}
