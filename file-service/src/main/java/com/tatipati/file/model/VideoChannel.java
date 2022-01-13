package com.tatipati.file.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;


/**
 * @author Mahdi Sharifi
 * @version 1.0.0
 * https://www.linkedin.com/in/mahdisharifi/
 * @since ۱/۳۰/۲۰۲۰
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class VideoChannel extends RepresentationModel<VideoChannel> {

    // ======================================
    // =             Attributes             =
    // ======================================
    private String id;
    private String source;
    private String title;
    private String bucket;

    private String goal; // English for kids , Kids Songs
    private String sourceUrl; //https://www.youtube.com/channel/UCGwA4GjY4nGMIYvaJiA0EGA
    private String bannerUrl; //https://www.youtube.com/channel/UCGwA4GjY4nGMIYvaJiA0EGA
    private int subscribersCounter;
    private String description;
    private String folder;
    private int maxAge;
    private int minAge;
    private int level;

}
