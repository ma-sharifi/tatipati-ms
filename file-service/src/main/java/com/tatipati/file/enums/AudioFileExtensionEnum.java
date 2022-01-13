package com.tatipati.file.enums;

/**
 * @author Mahdi Sharifi
 * @version 2020.1
 * https://www.linkedin.com/in/mahdisharifi/
 * @since ۱۱/۲/۲۰۲۰
 */
public enum AudioFileExtensionEnum {
    WAV("wav"),
    WAVE("wave"),
    MP3("mp3");

    private String value;

    public String getValue() {
        return value;
    }

    AudioFileExtensionEnum(String value) {
        this.value = value;
    }

    public static AudioFileExtensionEnum valueOfString(String v) {
        for (AudioFileExtensionEnum o : AudioFileExtensionEnum.values()) {
            if (o.getValue().equalsIgnoreCase(v) )
                return o;
        }
        return null;
    }

    /***
     * Extension without dot: mp3
     * @param fileExtension
     * @return
     */
    public static boolean isAudio(String fileExtension){
        if(valueOfString(fileExtension)!=null)
            return true;
        return false;
    }
}
