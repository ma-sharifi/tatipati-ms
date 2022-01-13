package com.tatipati.file.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.annotations.SerializedName;
import com.tatipati.file.util.DatabaseConstants;
import com.tatipati.file.util.serializer.GSONModel;
import com.tatipati.file.util.serializer.GsonExcludeField;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.tatipati.file.util.DatabaseConstants.*;


/**
 * @author Mahdi Sharifi
 * @version 1.0.0
 * https://www.linkedin.com/in/mahdisharifi/
 * @since 20/01/2020
 */
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "T_FILE"
        , indexes = {
        @Index(name = "IDX_FILE_" + MOBILE_NO_FIELD, columnList = MOBILE_NO_FIELD)
}
        , uniqueConstraints = {
        @UniqueConstraint(name = "UNQ_FILE_" + DIGEST, columnNames = {DIGEST})
        , @UniqueConstraint(name = "UNQ_FILE_NAM_OBJ_NAME", columnNames = {"BUCKET", "OBJECT_NAME"})
}
)
@NamedQueries({
        @NamedQuery(name = FileStub.FIND_ALL, query = "SELECT m FROM FileStub m order by m.createAt desc")
        , @NamedQuery(name = FileStub.FIND_BY_MOBILE_NO, query = "SELECT m FROM FileStub m WHERE m.mobileNo = :mobileNo")
        , @NamedQuery(name = FileStub.FIND_BY_BUCKET_OBJECT_NAME, query = "SELECT m FROM FileStub m WHERE m.bucket = :bucket AND m.objectName= :objectName")
        , @NamedQuery(name = FileStub.FIND_BY_DIGEST, query = "SELECT m FROM FileStub m WHERE m.digest = :digest")
        , @NamedQuery(name = FileStub.FIND_BY_CHANNEL_NAME, query = "SELECT m FROM FileStub m WHERE m.channel.id = :channelID")
        , @NamedQuery(name = FileStub.FIND_BY_UUID, query = "SELECT m FROM FileStub m WHERE m.uuid = :uuid")
        , @NamedQuery(name = FileStub.COUNT_BY_MOBILE_NO, query = "SELECT COUNT(m) FROM FileStub m WHERE m.mobileNo = :mobileNo")
        , @NamedQuery(name = FileStub.SUM_DURATION, query = "SELECT SUM(m.videoAttribute.durationMS) FROM FileStub m ")
        , @NamedQuery(name = FileStub.SUM_DURATION_BY_MOBILE_NO, query = "SELECT SUM(m.videoAttribute.durationMS) FROM FileStub m WHERE m.mobileNo = :mobileNo")
})
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "File.FindByGroupName",//"Post.findByIdWithCommentCount",
                query = "select post.PK_ID  as POST_ID ,tf.PK_ID as FILE_ID from t_file tf \n" +
                        " left join t_post post on post.FK_FILE = tf.PK_ID \n" +
                        " where tf.PK_ID in  " +
                        " (select FK_FILE FROM T_CATEGORY filegroup " +
                        "  WHERE filegroup.TITLE = ?groupName)" +
                        "GROUP BY post.PK_ID " +
                        "ORDER BY post.PK_ID asc  ",
                resultSetMapping = "File.FindByGroupNameMapping")
})
@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "File.FindByGroupNameMapping",
                classes = {
                        @ConstructorResult(
                                columns = {
                                        @ColumnResult(name = "POST_ID", type = Long.class),
                                        @ColumnResult(name = "FILE_ID", type = Long.class),
                                },
                                targetClass = PostFileFetchFromDB.class
                        )}
        )
})
@Getter  @Setter
public class FileStub extends GSONModel {

    // ======================================
    // =      Constant   Attributes         =
    // ======================================

    public static final String FIND_ALL = "FileStub.findAll";
    public static final String FIND_BY_MOBILE_NO = "FileStub.findByMobileNo";
    public static final String FIND_BY_BUCKET_OBJECT_NAME = "FileStub.findByBucketAndObjectName";
    public static final String FIND_BY_DIGEST = "FileStub.findByDigest";
    public static final String FIND_BY_CHANNEL_NAME = "FileStub.findByChannelName";
    public static final String FIND_BY_UUID = "FileStub.findByUUID";

    public static final String NATIVE_FIND_BY_GROUP_NAME = "FileStub.findByGroupName";
    public static final String COUNT_BY_MOBILE_NO = "FileStub.countByMobileNo";
    public static final String SUM_DURATION = "FileStub.sumDuration";
    public static final String SUM_DURATION_BY_MOBILE_NO = "FileStub.sumDurationByMobileNo";
    public static final String SUM_DURATION_BY_USERNAME = "FileStub.sumDurationByUsername";

    // ======================================
    // =             Attributes             =
    // ======================================

    @Id
    @Column(name = "PK_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "PARENT_ID")
    @GsonExcludeField
    private Long parentID;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "FK_PLAYLIST_IMAGE")
//    private Playlist playlist;

    @SerializedName("desc")
    @Column(name = "DESCRIPTION", length = 2048)
    private String description;

    @Column(name = "METADATA", length = 400)
    @GsonExcludeField
    private String metadata; // is Map<String,String> as json

    @Column(name = "ORIGINAL_FILE_NAME", length = 500)
    private String originalFileName;

    @Column(name = MOBILE_NO_FIELD, nullable = false)
    @GsonExcludeField
    private Long mobileNo;

    @Column(name = "OBJECT_NAME", length = 150)
    private String objectName;

    @Column(name = UUID_FIELD, length = DatabaseConstants.UUID_LENGTH)
    private String uuid;

    @Column(name = "FILE_TYPE", length = 20)
    private String fileType;

    @Column(name = "FK_VIDEO_CHANNEL")
    private String channel;//VideoChannel

    @Column(name = "SIZE")
    @GsonExcludeField
    private long size;

    @Column(name = DIGEST, length = 64)
    @GsonExcludeField
    private String digest;//d41d8cd98f00b204e9800998ecf8427e

    @Column(name = "TITLE", nullable = false)
    private String title; //Mike and Mia - Nursery Rhymes and Kids Songs
    //Bucket names must not contain uppercase characters or underscores.fd
    @Size(min = 3, max = 63, message = "##Bucket names must be at least 3 and no more than 63 characters long.")
    @Column(name = "BUCKET", length = 63, nullable = false)
//Bucket names must be at least 3 and no more than 63 characters long.
    @GsonExcludeField
    private String bucket;

    @Column(name = "ENDPOINT_STORE", length = 100)
    @GsonExcludeField
    private String endpointStore;//192.168.1.3:9000

    @Column(name = "ACCENT", length = 100)
    @GsonExcludeField
    private String accent;

    @Column(name = "TAGS", length = 200) // #alphablocks #alphabet #bbc #longman #learning_english
    private String tags;

    @Column(name = "YOUTUBE_URL", length = 250)
    private String youtubeUrl;
    @Column(name = "OTHER_URL", length = 250)
    private String otherUrl;


    @Column(name = "EXPIRE_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expireAt;

    @Column(name = "ACTIVE_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date activeAt;

    @SerializedName("language_level")
    @Column(name = "LANGUAGE_LEVEL")
    private Integer level;

    @Column(name = "GENRE", length = 100)
    private String genre;

    @GsonExcludeField
    @Column(name = "COUNTRY", length = 100)
    private String country;

    @Embedded
    @XmlElement(name = "attributes")
    @SerializedName("attributes")
    private VideoAttribute videoAttribute;

    //    @Enumerated(EnumType.STRING)
    @Column(name = "FILE_EXTENSION")
    @XmlElement(name = "file_extension")
    @SerializedName("file_extension")
    private String fileExtension;

    @Column(name = "CONTENT_TYPE",length = 200)
    @SerializedName("content_type")
    private String contentType;

    @Column(name = "SUMMARY_DATE_WRITTEN_AT")
    @Temporal(TemporalType.TIMESTAMP)
    @GsonExcludeField
    private Date summaryDateWrittenAt;

    @Column(name = "RESTRICTED_AGE")
    @GsonExcludeField
    private Integer restrictedAge;

    @Column(name = "MAX_AGE")
    @GsonExcludeField
    private Integer maxAge;

    @Column(name = "MIN_AGE")
    @GsonExcludeField
    private Integer minAge;

    @Column(name = "RELEASE_YEAR")
    @GsonExcludeField
    private Integer releaseYear;

    @Column(name = "DUBBED")
    @GsonExcludeField
    private boolean dubbed;

    @Column(name = "SUBTITLED")
    @GsonExcludeField
    private boolean subtitled;

    @Column(name = "RATE")
    @GsonExcludeField
    private float rate;

    @Column(name = "VIEWED_COUNTER")
    @GsonExcludeField
    private long viewedCounter;

    @Column(name = "DOWNLOADED_COUNTER")
    @GsonExcludeField
    private long downloadedCounter;

    @Column(name = "REPORTED_COUNTER")
    @GsonExcludeField
    private int reportedCounter;

    @Column(name = "LIKE_COUNTER")
    @GsonExcludeField
    private int likeCounter;

    @Column(name = "FAVORITE_COUNTER")
    @GsonExcludeField
    private int favoriteCounter;

    @Column(name = "SAVE_COUNTER")
    @GsonExcludeField
    private int saveCounter;

    @Column(name = "DISLIKE_COUNTER")
    @GsonExcludeField
    private int disLikeCounter;

    @Column(name = "PURCHASED")
    @GsonExcludeField
    private boolean purchased;

    @Column(name = "IS_PUBLIC")
    @XmlElement(name = "is_public")
    @SerializedName("is_public")
    private boolean isPublic;

    //--------
    @Column(name = "UPDATE_AT")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date updateAt;

    @Column(name = "VERSION")
    @GsonExcludeField
    protected int version;

    @Column(name = "ENABLED")
    @GsonExcludeField
    protected boolean enabled;


    @Column(name = "CREATE_AT")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date createAt;

    public FileStub() {
    }

    public FileStub(String uuid) {
        this.uuid = uuid;
    }

    @PrePersist
    private void prepareToPersist() {
//        setUuid("F"+RandomGenerator.generateUUID(32));
        if(uuid==null || "".equals(uuid))
        setUuid("F" + RandomStringUtils.randomAlphanumeric(5));
        this.createAt=(new Date());
    }

    public FileStub(Long mobileNo, String bucket, String objectName, String title, String channel) {
        this.mobileNo = mobileNo;
        this.bucket = bucket;
        this.objectName = objectName;
        this.title = title;
        this.channel = channel;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("VideoEntity{");
        sb.append("id=").append(id);
        sb.append(", originalFileName='").append(originalFileName).append('\'');
        sb.append(", mobileNo=").append(mobileNo);
        sb.append(", size=").append(size);
        sb.append(", title='").append(title).append('\'');
        sb.append(", bucket='").append(bucket).append('\'');
        sb.append(", contentType='").append(contentType).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
