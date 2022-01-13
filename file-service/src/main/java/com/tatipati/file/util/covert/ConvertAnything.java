package com.tatipati.file.util.covert;

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.HOURS;
import static java.util.concurrent.TimeUnit.MINUTES;

public class ConvertAnything {
    public static String videoDurationSecToString(long durationSecond) {
        String durationStr = "0:00";
        if (TimeUnit.MILLISECONDS.toHours(durationSecond) > 0)
            durationStr = (String.format("%2d:%02d:%02d", //Hour: 00:03:09
                    TimeUnit.MILLISECONDS.toHours(durationSecond),
                    (TimeUnit.MILLISECONDS.toMinutes(durationSecond) -
                            HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(durationSecond))), // The change is in this line
                    TimeUnit.MILLISECONDS.toSeconds(durationSecond) -
                            MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(durationSecond)))).trim();
        else
            durationStr = (String.format("%2d:%02d", //Hour: 00:03:09,
                    (TimeUnit.MILLISECONDS.toMinutes(durationSecond) -
                            HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(durationSecond))), // The change is in this line
                    TimeUnit.MILLISECONDS.toSeconds(durationSecond) -
                            MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(durationSecond)))).trim();
        return durationStr;
    }

    public static String byteToString(long l) {
        String str = "";
        if (l < 1024) {
            return l + " B";
        }
        long kByte = (l / 1024);
        if (kByte < 1024) {
            return kByte + " KB";
        }
        long mByte = kByte / 1024;
        if (mByte < 1024) {
            return mByte + " MB";
        }

        long gByte = mByte / 1024;
        if (gByte < 1024) {
            return gByte + " GB";
        }

        long tByte = gByte / 1024;
        if (tByte < 1024) {
            return tByte + " TB";
        }

        return str;
    }
}
