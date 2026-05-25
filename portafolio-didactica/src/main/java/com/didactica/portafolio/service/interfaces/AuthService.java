package com.didactica.portafolio.service.interfaces;

import com.didactica.portafolio.dto.request.LoginRequest;
import com.didactica.portafolio.dto.response.AuthResponse;

public interface AuthService {
    AuthResponse login(LoginRequest request);
}
