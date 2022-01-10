package com.tatipati.file.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tatipati.file.util.serializer.GSONModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * @author Mahdi Sharifi
 * @version 1.0.0
 * https://www.linkedin.com/in/mahdisharifi/
 * @since ۵/۲۹/۲۰۲۰
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VideoChannelDto extends GSONModel {
    private String id;
    private String title;
    private String url;
}
