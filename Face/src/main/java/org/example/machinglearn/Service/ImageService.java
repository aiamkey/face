package org.example.machinglearn.Service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    Object getTongue(MultipartFile file);

    Object postTongue(MultipartFile file);
}
