package com.tatipati.file.service;

import com.tatipati.file.dto.FileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class FileService {

    @Autowired
    private MessageSource messageSource;


    public  String createFile(FileDto fileDto, Locale locale){
        String responseMessage=null;
        responseMessage=String.format(messageSource.getMessage("license.create.message",null,locale)
               ,fileDto.getUuid());
        fileDto.setId(1l);
        fileDto.setUuid("UUID1");
        return responseMessage;
    }
    public FileDto getFile(String uuid){
        FileDto fileDto=new FileDto();
        fileDto.setId(1l);
        fileDto.setUuid("UUID1");
        return fileDto;
    }

    public String updateFile(FileDto fileDto){
        String responseMessage=null;
        if(fileDto!=null){
          fileDto.setUuid("new update uuid");
          responseMessage=String.format("This is the put and the object is: %s",fileDto.getUuid());
        }
        return responseMessage;
    }

    public String  deleteFile(String uuid){
        String responseMessage=null;
        responseMessage=String.format("This is the Delete file uuid: %s",uuid);
        return responseMessage;
    }

}
