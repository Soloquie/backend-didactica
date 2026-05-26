package com.didactica.portafolio.service.interfaces;

import com.didactica.portafolio.dto.request.GrupoInfoRequest;
import com.didactica.portafolio.dto.response.GrupoInfoResponse;
import org.springframework.web.multipart.MultipartFile;

public interface GrupoInfoService {
    GrupoInfoResponse getPublicGrupoInfo();

    GrupoInfoResponse getAdminGrupoInfo();

    GrupoInfoResponse update(GrupoInfoRequest request);

    GrupoInfoResponse uploadImage(MultipartFile archivo);
}
