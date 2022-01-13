package com.tatipati.file.service;

import com.tatipati.file.dto.FileDto;
import com.tatipati.file.mapper.FileMapper;
import com.tatipati.file.model.FileStub;
import com.tatipati.file.repository.FileStubRepository;
import com.tatipati.file.util.serializer.JSONFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.io.FileDescriptor;
import java.util.Locale;
import java.util.Optional;

@Service
public class FileService {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private FileStubRepository fileRepository;

    @Autowired
    private FileMapper fileMapper;

    public  String createFile(FileDto fileDto, Locale locale){
        String responseMessage=null;
        responseMessage=String.format(messageSource.getMessage("license.create.message",null,locale)
               ,fileDto.getUuid());
        fileDto.setId(1l);
        fileDto.setUuid("UUID1");
        return responseMessage;
    }
    public FileDto getFile(Long id){
       Optional<FileStub> fileStubOptional= fileRepository.findById(id);
       if(fileStubOptional.isPresent()) {
           FileStub fileStub=fileStubOptional.get();
           System.out.println("#fileStub: "+fileStub.toJSON());
           FileDto dto=fileMapper.toDto( fileStub );
           System.err.println("#fileDto: "+ JSONFormatter.toJSON(dto));;
           return dto;
       }
        FileDto fileDto=new FileDto();
        fileDto.setId(1l);
        fileDto.setUuid("UUID1");
        return fileDto;
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

    public String  deleteFile(Long id){
        String responseMessage=null;
        responseMessage=String.format("This is the Delete file uuid: %s",id);
        return responseMessage;
    }

}
