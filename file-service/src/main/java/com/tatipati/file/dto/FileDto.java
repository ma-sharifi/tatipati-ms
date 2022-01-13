package com.tatipati.file.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;

/**
 * @author Mahdi Sharifi
 * @version 1.0.0
 * https://www.linkedin.com/in/mahdisharifi/
 * @since ۵/۲۹/۲۰۲۰
 */
//@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FileDto{ // extends RepresentationModel<FileDto>
    @SerializedName("id")
    private Long id;
    private String url;
    @SerializedName("download_uuid_url")
    @JsonProperty("download_uuid_url")
    private String downloadUuidUrl;
    private String play;
    private String title;
    @JsonProperty("file_limit")
    @SerializedName("file_limit")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LimitDto fileLimit;
    @JsonProperty("content_type")
    private String contentType;
    @JsonProperty("mime_type")
    private String mimeType;
    private String bucket;
    @JsonProperty("price")
    private long price;
    @JsonProperty("real_name")
    private String realName;
    private String extension;
    @SerializedName("size_byte")
    @JsonProperty("size_byte")
    private Long sizeByte;
    @SerializedName("size_string")
    @JsonProperty("size_string")
    private String sizeString;
    private String type;
    @JsonProperty("mobile_no")
    private String mobileNo;
    @JsonProperty("post_uuid")
    private String postUuid;
    private Long postId;
    private String token;
    private boolean isFree;
    private String uuid;

    @SerializedName("created_at")
    @JsonProperty("created_at")
    private String createdAt;
    @JsonIgnore
    private Date date;
    @JsonProperty("created_at_string")
    private String createdAtString;

    @SerializedName("on_bottom")
    @JsonProperty("on_bottom")
    private String onBottom;
    @SerializedName("on_top")
    @JsonProperty("on_top")
    private String onTop;

    private String quality;

    @JsonProperty("quality_size")
    private String qualitySize;
    @JsonProperty("quality_duration")
    private String qualityDuration;
    @SerializedName("duration_ms")
    @JsonProperty("duration_ms")
    private Long durationMS;
    @SerializedName("duration_string")
    @JsonProperty("duration_string")
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
    @JsonProperty("is_public")
    private Boolean isPublic;





    public FileDto(Long id) {
        this.id = id;
    }

}
