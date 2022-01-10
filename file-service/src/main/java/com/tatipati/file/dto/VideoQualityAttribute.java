package com.tatipati.file.dto;

import com.tatipati.file.util.serializer.GSONModel;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author Mahdi Sharifi
 * @version 1.0.0
 * https://www.linkedin.com/in/mahdisharifi/
 * @since ۵/۲۹/۲۰۲۰
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class VideoQualityAttribute extends GSONModel {
    private int height;
    private int width;

    public VideoQualityAttribute() {
    }

    public VideoQualityAttribute(int height, int width) {
        this.height = height;
        this.width = width;
    }
}
