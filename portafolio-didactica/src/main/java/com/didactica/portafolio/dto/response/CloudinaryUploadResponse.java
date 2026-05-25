package com.didactica.portafolio.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CloudinaryUploadResponse {
    private String url;
    private String publicId;
    private String originalFilename;
    private String contentType;
    private Long size;
}
