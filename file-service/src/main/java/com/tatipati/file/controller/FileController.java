package com.tatipati.file.controller;

import com.tatipati.file.dto.FileDto;
import com.tatipati.file.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/v1/files")
public class FileController {

    @Autowired
    private FileService fileService;

    @GetMapping
    @ResponseBody
    public String sayHello() {
        return "HELLO ";
    }
    @PostMapping
    public ResponseEntity<String> createFile(@RequestBody FileDto fileDto) {
        String fileDtoResponse = fileService.createFile(fileDto,Locale.US);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setLocation(linkTo(FileController.class).slash(uuid).toUri());
//        return ResponseEntity
//                .status(HttpStatus.OK)//
//                .headers(headers)
//                .body(fileDto);
        return ResponseEntity.ok(fileDtoResponse);
    }

    @GetMapping("/{file-uuid}")
    public ResponseEntity<FileDto> getFile(@PathVariable("file-uuid") String uuid) {
        FileDto fileDto = fileService.getFile(uuid);
        fileDto.add(
                linkTo(methodOn(FileController.class).getFile(uuid)).withSelfRel(),
                linkTo(methodOn(FileController.class).createFile(null)).withRel("create-file"),
                linkTo(methodOn(FileController.class).updateFile(null)).withRel("update-file"),
                linkTo(methodOn(FileController.class).deleteFile(uuid)).withRel("delete-file") //health-path
        );
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(linkTo(FileController.class).slash(uuid).toUri());
        //status(HttpStatus.CREATED).location(location)

        return ResponseEntity
                .status(HttpStatus.OK)//
                .headers(headers)
                .body(fileDto);
    }

    @PutMapping
    public ResponseEntity<String> updateFile(@RequestBody FileDto fileDto) {
        return ResponseEntity.ok(fileService.updateFile(fileDto));
    }

    @DeleteMapping("/{file-uuid}")
    public ResponseEntity<String> deleteFile(@PathVariable("file-uuid") String uuid){
        return ResponseEntity.ok(fileService.deleteFile(uuid));
    }
}
