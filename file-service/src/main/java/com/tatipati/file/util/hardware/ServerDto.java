package com.tatipati.file.util.hardware;

import com.google.gson.annotations.SerializedName;
import com.tatipati.file.util.AppUtil;
import com.tatipati.file.util.date.DateUtil;
import com.tatipati.file.util.serializer.GSONModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.xml.bind.annotation.XmlRootElement;
import java.net.InetAddress;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class ServerDto extends GSONModel {

    @SerializedName("@timestamp")
    private final String dateTimeZone= DateUtil.getDateTimeWithTimeZone(new Date());
    private final String version= AppUtil.APP_VERSION;
    private String serverAddress = "UnKnown";
    private String serverName = "UnKnown";


    public ServerDto() {
        try {
            this.serverAddress = InetAddress.getLocalHost().getHostAddress();
            this.serverName = InetAddress.getLocalHost().getHostName();
        } catch (Exception ex) {
        }
    }
}
