package org.example.machinglearn.Controller;

import org.example.machinglearn.Service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/users")
public class TongueController {

    @Autowired
    private ImageService imageService;


    @PostMapping("/tongue-controller")
    public Object tonGue(@RequestParam("file") MultipartFile file)
    {
        if (!file.isEmpty()) {
            try {
                return imageService.postTongue(file);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }else {
            return null;
        }
    }

    @GetMapping("/tongue")
    public Object gettonGue(@RequestParam("file") MultipartFile file)
    {
        if (!file.isEmpty()) {
            try {
                return imageService.getTongue(file);
            }catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }else {
            return null;
        }
    }
}
