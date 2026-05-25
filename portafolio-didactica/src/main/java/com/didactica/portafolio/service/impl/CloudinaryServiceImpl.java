package com.didactica.portafolio.service.impl;

import com.cloudinary.Cloudinary;
import com.didactica.portafolio.dto.response.CloudinaryUploadResponse;
import com.didactica.portafolio.exception.BadRequestException;
import com.didactica.portafolio.service.interfaces.CloudinaryService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {
    private final Cloudinary cloudinary;

    public CloudinaryServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public CloudinaryUploadResponse upload(MultipartFile file, String folder) {
        if (file == null || file.isEmpty()) {
            throw new BadRequestException("El archivo es obligatorio");
        }
        try {
            var result = cloudinary.uploader().upload(file.getBytes(), Map.of(
                    "folder", folder,
                    "resource_type", "auto"
            ));
            return CloudinaryUploadResponse.builder()
                    .url((String) result.get("secure_url"))
                    .publicId((String) result.get("public_id"))
                    .originalFilename(file.getOriginalFilename())
                    .contentType(file.getContentType())
                    .size(file.getSize())
                    .build();
        } catch (IOException ex) {
            throw new BadRequestException("No se pudo subir el archivo a Cloudinary");
        }
    }
}
