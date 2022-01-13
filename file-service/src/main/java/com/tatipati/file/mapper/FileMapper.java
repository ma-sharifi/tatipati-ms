package com.tatipati.file.mapper;

import com.tatipati.file.controller.ControllerConstant;
import com.tatipati.file.dto.FileDto;
import com.tatipati.file.dto.VideoAttributeDto;
import com.tatipati.file.enums.AudioFileExtensionEnum;
import com.tatipati.file.model.FileStub;
import com.tatipati.file.enums.VideoQualityEnum;
import com.tatipati.file.util.AppUtil;
import com.tatipati.file.util.covert.ConvertAnything;
import com.tatipati.file.util.date.DateUtil;
import org.mapstruct.*;

import java.text.SimpleDateFormat;


@Mapper(uses = {VideoAttributeMapper.class}, componentModel = "spring")
public interface FileMapper {

    @AfterMapping
    default void updateResult(@MappingTarget FileDto fileDto) {
        fileDto.setCreatedAt(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZ").format(fileDto.getDate()));
        fileDto.setCreatedAtString(DateUtil.toPersianString(fileDto.getDate()));

         String query="";
//        if(hash!=null && !"".equals(hash))
//            query="?hash="+hash;
        fileDto.setDownloadUuidUrl(AppUtil.ADDRESS_CONTEXT_PATH_SCHEMA + "/" + ControllerConstant.SERVLET_PUBLIC + "/files/uuid/" + fileDto.getUuid() + "/dl"+query);//mobile_no=" + mobileNo + "&post_uuid=" + postUuid + "&is_free=" + isFree + "&bucket=" + fileStub.getBucket()+
        fileDto.setPlay(AppUtil.ADDRESS_CONTEXT_PATH_SCHEMA + "/" + ControllerConstant.SERVLET_PUBLIC + "/files/uuid/" + fileDto.getUuid() + "/dl"+query);
        String byteToString = ConvertAnything.byteToString(fileDto.getSizeByte());
        fileDto.setSizeString(byteToString);
        VideoAttributeDto videoAttributeDto=fileDto.getVideoAttribute();
        if(videoAttributeDto!=null) {
            fileDto.setDurationMS(videoAttributeDto.getDurationMS());
            fileDto.setDurationString(ConvertAnything.videoDurationSecToString(fileDto.getDurationMS()));
            if(videoAttributeDto.getImageHeight()!=0) {
                String quality = VideoQualityEnum.getQualityString(videoAttributeDto.getImageHeight());
                fileDto.setQuality(quality);

                if (AudioFileExtensionEnum.isAudio(fileDto.getExtension())) {
                    quality = "" + (videoAttributeDto.getAudioBitrate() / 1000) + "k";
                }
                fileDto.setOnBottom((quality + " " + fileDto.getDurationString()).trim());
                fileDto.setQualityDuration((quality + " " + fileDto.getDurationString()).trim());
                fileDto.setQuality(quality);
                fileDto.setQualitySize(quality + "/" + byteToString);
            }
        }
    }

    @Mappings({
            @Mapping(source = "createAt", target = "date")
            , @Mapping(source = "size", target = "sizeByte")
            , @Mapping(source = "fileExtension", target = "extension")
            , @Mapping(source = "fileType", target = "type")
            , @Mapping(source = "originalFileName", target = "realName")
            , @Mapping(source = "public", target = "isPublic")
    })
     FileDto toDto(FileStub file);

    //    FileStub fromDto(FileDto dto);
//
////    public static FileDto toFileDto(String mobileNo, Post post, FileStub fileStub,String hash) {
////        long price = 0;
////        if (fileStub == null) {// WHY?
////            return new FileDto();
////        }
////        FileDto fileDto = toFileDtoShort(fileStub);
////        LimitDto limitDto=new LimitDto(15,15);
////        fileDto.setFileLimit(limitDto);
////        if (post != null) {
////            price = post.getPrice();
////            fileDto.setPostUuid(post.getUuid());
////            fileDto.setPostId(post.getId());
////            fileDto.setPrice(price);
////        }
////
////        String query="";
////        if(hash!=null && !"".equals(hash))
////            query="?hash="+hash;
////
////        fileDto.setDownloadUuidUrl(AppUtil.TATIPATI_ADDRESS_ACONTEXT_PATH + "/" + SERVLET_PUBLIC + "/files/uuid/" + fileStub.getUuid() + "/dl"+query);//mobile_no=" + mobileNo + "&post_uuid=" + postUuid + "&is_free=" + isFree + "&bucket=" + fileStub.getBucket()+
////        fileDto.setPlay(AppUtil.TATIPATI_ADDRESS_ACONTEXT_PATH + "/" + SERVLET_PUBLIC + "/files/uuid/" + fileStub.getUuid() + "/dl"+query);
////
////
////        if (fileStub.getChannel() != null)
////            fileDto.setChannelDto(new VideoChannelDto(fileStub.getChannel().getId(), fileStub.getChannel().getTitle(), fileStub.getChannel().getSourceUrl()));
////        fileDto.setEnabled(fileStub.isEnabled());
////        fileDto.setIsPublic(fileStub.isPublic());
////        fileDto.setContentType(fileStub.getContentType());
////        fileDto.setMinAge(fileStub.getMinAge());
////        fileDto.setMaxAge(fileStub.getMaxAge());
////        fileDto.setType(fileStub.getFileType());
////        fileDto.setCreatedAtString(TimeUtil.getPostDate(fileStub.getCreateAt()));
////        fileDto.setCreatedAt(TimeUtil.getDateTimeWithTimeZone(fileStub.getCreateAt()));
////        fileDto.setRealName(fileStub.getOriginalFileName());
////
////        String byteToString = HardwareAllocated.convBytToString(fileStub.getSize());
////        fileDto.setSizeString(byteToString);
////
////        VideoAttribute videoAttribute = fileStub.getVideoAttribute();
////        if (ImageFileExtensionEnum.isImage(fileDto.getExtension())) {
////            if (videoAttribute != null) {
////                fileDto.setHeight(videoAttribute.getImageHeight());
////                fileDto.setWidth(videoAttribute.getImageWidth());
////            }
////        }
////        if (fileStub.getVideoAttribute() != null && (VideoFileExtensionEnum.isVideo(fileDto.getExtension()) || AudioFileExtensionEnum.isAudio(fileDto.getExtension()))) {
////            String durationString = AppUtil.videoDurationSecToString(videoAttribute.getDurationMS() / 1000);
////            if (videoAttribute.getImageHeight() != 0) {
////                VideoAttributeDto videoAttributeDto = new VideoAttributeDto(new VideoQualityAttribute(videoAttribute.getImageHeight(), videoAttribute.getImageWidth()), videoAttribute.getDurationMS(), durationString);
////                fileDto.setVideoAttribute(videoAttributeDto);
////            }
////
////            fileDto.setDurationMS(videoAttribute.getDurationMS());
////            fileDto.setDurationString(durationString);
////            String quality = VideoQualityEnum.getQualityString(videoAttribute.getImageHeight());
////            if (AudioFileExtensionEnum.isAudio(fileDto.getExtension())) {
////                quality = "" + (fileStub.getVideoAttribute().getAudioBitrate() / 1000) + "k";
////            }
////            fileDto.setOnBottom((quality + " " + durationString).trim());
////            fileDto.setQualityDuration((quality + " " + durationString).trim());
////            fileDto.setQuality(quality);
////            fileDto.setQualitySize(quality + "/" + byteToString);
////
////        }
////        if (fileStub.getCategories() != null) {
////            List<CategoryDto> groupList = new ArrayList<>();
////            for (CategoryEntity categoryEntity : fileStub.getCategories()) {
////                groupList.add(new CategoryDto(categoryEntity.getName(), categoryEntity.getTitle()));
////            }
////            fileDto.setCategories(groupList);
////        }
////        return fileDto;
////    }
}

