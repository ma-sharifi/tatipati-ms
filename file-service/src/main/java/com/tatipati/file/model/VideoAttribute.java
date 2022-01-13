package com.tatipati.file.model;

import com.tatipati.file.enums.VideoReleaseFormatEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Mahdi Sharifi
 * @version 1.0.0
 * https://www.linkedin.com/in/mahdisharifi/
 * @since 15/02/2020
 */
@EqualsAndHashCode(callSuper = true)
@Embeddable
@Data
public class VideoAttribute extends RepresentationModel<VideoAttribute> {
    @Column(name = "DURATION_MS")
    private long durationMS; //LengthInTime ->189753467

    @Column(name = "IMAGE_WIDTH")
    private int imageWidth;//1280

    @Column(name = "IMAGE_HEIGHT")
    private int imageHeight;//720

    @Column(name = "FORMAT", length = 50)
    private String format; //mov,mp4,m4a,3gp,3g2,mj2

    @Column(name = "VIDEO_FRAME_RATE")
    private double videoFrameRate;//24.0

    @Column(name = "VIDEO_BITRATE")
    private int videoBitrate;//454046

    @Column(name = "VIDEO_CREATE_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date videoCreateAt;//2018-09-20T07:47:18.000000Z

    @Column(name = "LENGTH_IN_FRAMES")
    private int lengthInFrames;//4554

    @Column(name = "AUDIO_BITRATE")
    private int AudioBitrate;//125588 OR 2116800

    @Column(name = "AUDIO_CHANNELS")
    private int audioChannels;//2

    @Column(name = "MAJOR_BRAND", length = 20)
    private String majoBrand;//mp42

    @Column(name = "COMPATIBLE_BRAND", length = 20)
    private String compatibleBrands; //isommp42
    //--------------------
    @Column(name = "VIDEO_RELEASE_FORMAT")
    @Enumerated(EnumType.STRING)
    private VideoReleaseFormatEnum videoReleaseFormat;//VideoReleaseFormatEnum

    @Column(name = "VIDEO_QUALITY")
    private Integer videoQuality;
}
