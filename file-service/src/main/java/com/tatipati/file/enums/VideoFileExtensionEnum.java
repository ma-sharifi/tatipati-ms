package com.tatipati.file.enums;

/**
 * @author Mahdi Sharifi
 * @version 1.0.0
 * https://www.linkedin.com/in/mahdisharifi/
 * @since 21/01/2020
 *
 *  Video Containers
 */
public enum VideoFileExtensionEnum {
    MKV("mkv"),
    MP4("mp4"),
    AVI("avi"),
    TS("ts"),
    GP("3gp"),
    WMV("wmv"),
    LV("flv"),
    WEBM("webm"),
    MOV("mov");

    private String value;

    public String getValue() {
        return value;
    }

    VideoFileExtensionEnum(String value) {
        this.value = value;
    }

    public static VideoFileExtensionEnum valueOfString(String v) {
        for (VideoFileExtensionEnum o : VideoFileExtensionEnum.values()) {
            if (o.getValue().equalsIgnoreCase(v) )
                return o;
        }
        return null;
    }

    /***
     * Extension without dot: mp4
     * @param fileExtension
     * @return
     */
    public static boolean isVideo(String fileExtension){
        if(valueOfString(fileExtension)!=null)
            return true;
        return false;
    }
}
