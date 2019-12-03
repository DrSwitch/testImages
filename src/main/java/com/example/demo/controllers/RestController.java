package com.example.demo.controllers;

import com.example.demo.entity.RequestForm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URL;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @PostMapping("setImages")
    public RequestForm setImages(@RequestParam("links") List<String> linkList) {
        RequestForm requestForm = new RequestForm();
        try {
            requestForm.setSuccess(true);

            for (String link : linkList) {
                getFile(link);
            }

        } catch (Exception e) {
            requestForm.setSuccess(false);
            requestForm.setData(e.toString());
        }

        return requestForm;
    }

    private File getFile(String link) throws IOException {

        String fileName = "loaded.jpg";
        URL url = new URL(link);

        InputStream in = new BufferedInputStream(url.openStream());
        FileOutputStream out = new FileOutputStream(fileName);

        System.out.println("Download start!");

        byte buffer[] = new byte[1024];
        while(in.read(buffer) != -1) {
            out.write(buffer);
        }
        System.out.println("Download finish!");

        in.close();
        out.close();

        return new File("");
    }
}
