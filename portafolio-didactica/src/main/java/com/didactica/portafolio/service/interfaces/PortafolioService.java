package com.didactica.portafolio.service.interfaces;

import com.didactica.portafolio.dto.request.PortafolioRequest;
import com.didactica.portafolio.dto.response.PortafolioResponse;

import java.util.List;

public interface PortafolioService {
    PortafolioResponse getPublicPortafolio();
    List<PortafolioResponse> getAdminPortafolios();
    PortafolioResponse update(Long id, PortafolioRequest request);
}
