package com.vocco.api.controller;

import com.vocco.api.infra.aws.FilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("arquivos")
@RestController
public class FilesController {

    @Autowired
    private FilesService s3Service;;
    
    @GetMapping("download/{fileName}")
    public ResponseEntity<Resource> getImage(@PathVariable String fileName) {
        Resource resource = s3Service.buscarArquivo(fileName);
        MediaType mediaType = MediaType.IMAGE_PNG;
        if (fileName.toLowerCase().endsWith(".jpg") || fileName.toLowerCase().endsWith(".jpeg")) {
            mediaType = MediaType.IMAGE_JPEG;
        }

        return ResponseEntity.ok()
                .contentType(mediaType)
                .body(resource);
    }


    @DeleteMapping("{filename}")
    public  String deleteFile(@PathVariable("filename") String filename){
        return s3Service.excluirArquivo(filename);
    }

    @GetMapping("list")
    public List<String> getAllFiles(){
        return s3Service.listarArquivos();
    }
}
