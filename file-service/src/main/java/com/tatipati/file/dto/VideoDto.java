package com.tatipati.file.dto;

import com.google.gson.annotations.SerializedName;
import com.tatipati.file.util.serializer.GSONModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Mahdi Sharifi
 * @version 1.0.0
 * https://www.linkedin.com/in/mahdisharifi/
 * @since 29/01/2020
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class VideoDto extends GSONModel {
    private long id;
    private String playListTitle;
    private String title;
    private String bucket;
    private Long size;
    private Attribute attribute;
    @SerializedName("duration_ms")
    private Long durationMS;
    @SerializedName("duration_string")
    private String durationString;

    private boolean favorited;
    private String iconUrl;
    private String url;

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class Attribute extends GSONModel {
        private int height;
        private int width;

        public Attribute(int height, int width) {
            this.height = height;
            this.width = width;
        }
    }
}
