package com.tatipati.file.util;

/**
 * @author Mahdi Sharifi
 * @version 1.0.0
 * https://www.linkedin.com/in/mahdisharifi/
 * @since 22/01/2020
 */
public enum DatabaseConstants {
    INSTANCE;
    public static final String TABLE_PREFIX = "T_";
    public static final String SUGGESTION = "SUGGESTION";
    public static final String MESSAGE_INBOX = "MESSAGE_INBOX";


    public static final boolean CACHEABLE = false;

    public static final int UUID_LENGTH = 30;
    public static final int USERNAME_LENGTH = 75;//mobileNo!firstName+dot+lastName
    public static final int URL_LENGTH = 500;
    public static final int TITLE_FIELD_LENGTH = 100;
    public static final int POST_TITLE_FIELD_LENGTH = 64;
    public static final int NAME_FIELD_LENGTH = 100;

    public static final String USERNAME_FIELD = "USERNAME";
    public static final String DIGEST = "DIGEST";
    public static final String NAME_FIELD = "NAME";
    public static final String TITLE_FIELD = "TITLE";
    public static final String CREATE_AT_FIELD = "CREATE_AT";
    public static final String UUID_FIELD = "UUID";
    public static final String MOBILE_NO_FIELD = "MOBILE_NO";
}
