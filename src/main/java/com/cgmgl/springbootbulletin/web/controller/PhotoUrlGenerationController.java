package com.cgmgl.springbootbulletin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cgmgl.springbootbulletin.helper.PhotoUploadHelper;

@Controller
public class PhotoUrlGenerationController {
    @GetMapping("/uploads/{photo_url}")
    public String generatePhotoUrl(@PathVariable("photo_url") String photo_url) {
        return PhotoUploadHelper.getRootStoragePath() + photo_url;
    }

}
