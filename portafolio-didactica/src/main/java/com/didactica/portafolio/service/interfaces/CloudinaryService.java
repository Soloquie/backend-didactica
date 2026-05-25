package com.didactica.portafolio.service.interfaces;

import com.didactica.portafolio.dto.response.CloudinaryUploadResponse;
import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryService {
    CloudinaryUploadResponse upload(MultipartFile file, String folder);
}
