package com.tatipati.file.enums;

/**
 * @author Mahdi Sharifi
 * @version 1.0.0
 * https://www.linkedin.com/in/mahdisharifi/
 * @since 20/01/2020
 */

public enum VideoReleaseFormatEnum {
    NONE("NONE"),
    CAM("CAM"),
    HDTS("HDTS"),
    WP("WP"),
    HDTC("HDTC"),
    PPVRip("PPVRip"),
    DVDSCR("DVDSCR"),
    R5("R5"),
    DVD_RIP("DVDRip"),
    FLL_RIP("Full-Rip"),
    HDTV("HDTV"),
    VOD_RIP("VODRip"),
    WEB_DL("WEB-DL"),
    WEB_RIP("WEBRip"),
    WEB_CAP("WEB-Cap"),
    HD_RIP("HD-Rip"),
    BLUE_RAY("BluRay");

    private String value;

    VideoReleaseFormatEnum(String value) {
        this.value = value;
    }
}
