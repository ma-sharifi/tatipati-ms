package com.tatipati.file.enums;

import lombok.Getter;

/**
 * @author Mahdi Sharifi
 * @version 1.0.0
 * https://www.linkedin.com/in/mahdisharifi/
 * @since 21/01/2020
 */
@Getter
public enum VideoQualityEnum {
    NONE(0, 0, "NONE"),//352 x 240 (240p) (SD)
    Q240P(352, 240, "240p"),//352 x 240 (240p) (SD)
    Q360P(480, 360, "360p"), //480 x 360 (360p)
    Q480P(858, 480, "480p"), //858 x 480 (480p)
    Q720P(1280, 720, "HD"), //1280 x 720 (720p) (Half HD)
    Q1080P(1920, 1080, "FHD"), //1920 x 1080 (1080p) (Full HD)
//    Q1080P(1920, 1080, "Full HD"), //1920 x 1080 (1080p) (Full HD)
    Q2160P(3860, 2160, "4K"), // 3860 x 2160 (2160p) (Ultra-HD) (4K)
    Q4320P(7680, 4320, "8K"); //8K	7680 x 4320	33,177,600

    public static VideoQualityEnum valueOf(int height) {
        for (VideoQualityEnum o : VideoQualityEnum.values()) {
            if (o.getResolutionY() == height)
                return o;
        }
        return NONE;
    }
    public static String getQualityString(int height) {
        for (VideoQualityEnum o : VideoQualityEnum.values()) {
            if (o.getResolutionY() == height)
                return o.getQualityString();
        }
        return height+"p";
    }

    public String getPixelsString() {
        return pixelsString;
    }

    public int getResolutionX() {
        return resolutionX;
    }

    public int getResolutionY() {
        return resolutionY;
    }

    public String getQualityString() {
        return qualityString;
    }

    //352 x 240 (240p) (SD) (VCD Players)
//            480 x 360 (360p)
//            858 x 480 (480p)
//            1280 x 720 (720p) (HD) (Some HDTVs)
//            1920 x 1080 (1080p) (HD) (Blu-Ray Players, HDTV)
//            3860 x 2160 (2160p) (Ultra-HD) (4K Players / Televisions)
//
//    Apart from these, there are other variants of resolutions as well
//640 x 480 (VGA, Standard Definition TVs)
//            1280 x 544 (Wide-Screen movies)
//            1920 x 816 or 1920 x 800 (Wide-screen movies)

    private String pixelsString;//720
    private int resolutionX;//960×720	-> 960
    private int resolutionY;//pixels 960×720	-> 720
    private String qualityString;// 4K

    public String getPixel() {
        return resolutionY + "P";//String pixelsString,
    }


    VideoQualityEnum(int resolutionX, int resolutionY, String qualityString) {
        this.resolutionX = resolutionX;
        this.resolutionY = resolutionY;
        this.qualityString = qualityString;
    }

    public static VideoQualityEnum valueOfY(int v) {
        for (VideoQualityEnum o : VideoQualityEnum.values()) {
            if (o.getResolutionY()==v )
                return o;
        }
        return NONE;
    }
}
