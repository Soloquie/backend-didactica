package com.didactica.portafolio.dto.response;

import com.didactica.portafolio.enums.RolUsuario;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {
    private String token;
    @Builder.Default
    private String tipo = "Bearer";
    private String nombre;
    private String correo;
    private RolUsuario rol;
}
