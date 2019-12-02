package com.example.demo.controllers;

import com.example.demo.entity.RequestForm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @PostMapping("setImages")
    public RequestForm setImages(@RequestParam("file") List<MultipartFile> multipartFileList) {
        RequestForm requestForm = new RequestForm();
        try {
            requestForm.setSuccess(true);

            for (MultipartFile multipartFile : multipartFileList) {
                compressFile(multipartFile);
            }

        } catch (Exception e) {
            requestForm.setSuccess(false);
            requestForm.setData(e.toString());
        }

        return requestForm;
    }

    private File compressFile(MultipartFile multipartFile) {

        System.out.println("multipartFile.getOriginalFilename() = " + multipartFile.getOriginalFilename());


        return new File("");
    }
}
