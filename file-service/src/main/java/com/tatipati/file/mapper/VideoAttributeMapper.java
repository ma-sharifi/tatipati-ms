package com.tatipati.file.mapper;

import com.tatipati.file.dto.FileDto;
import com.tatipati.file.dto.VideoAttributeDto;
import com.tatipati.file.model.VideoAttribute;
import com.tatipati.file.util.covert.ConvertAnything;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface VideoAttributeMapper {
    @AfterMapping
    default void updateResult(@MappingTarget VideoAttributeDto dto) {
        dto.setDurationString(ConvertAnything.videoDurationSecToString(dto.getDurationMS()));
    }
    VideoAttributeDto toDto(VideoAttribute videoAttribute);
}
