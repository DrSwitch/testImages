package com.example.demo.controllers;

import com.example.demo.entity.RequestForm;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.Date;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    private static final int IMG_WIDTH = 100;
    private static final int IMG_HEIGHT = 100;

    @PostMapping("setImages")
    public RequestForm setImages(@RequestParam("links") List<String> linkList) {
        RequestForm requestForm = new RequestForm();
        try {
            requestForm.setSuccess(true);

            for (String link : linkList) {
                saveFile(link);
            }

        } catch (Exception e) {
            requestForm.setSuccess(false);
            requestForm.setData(e.toString());
        }

        return requestForm;
    }

    private String saveFile(String link) throws IOException {

        String fileName = "loaded-" + new Date() + ".jpg";
        URL url = new URL(link);

        InputStream in = new BufferedInputStream(url.openStream());
        FileOutputStream out = new FileOutputStream(fileName);

        try {
            BufferedImage image = ImageIO.read(new ByteArrayInputStream(in.readAllBytes()));
            ImageIO.write(resizeImage(image, image.getType()), "jpg", out);
        } catch (IOException e) {
            e.printStackTrace();
        }

        in.close();
        out.close();

        return fileName;
    }

    private static BufferedImage resizeImage(BufferedImage originalImage, int type) {
        BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
        g.dispose();

        return resizedImage;
    }}
