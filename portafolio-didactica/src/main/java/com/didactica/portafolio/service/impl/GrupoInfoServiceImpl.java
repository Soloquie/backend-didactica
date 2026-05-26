package com.didactica.portafolio.service.impl;

import com.didactica.portafolio.dto.request.GrupoInfoRequest;
import com.didactica.portafolio.dto.response.GrupoInfoResponse;
import com.didactica.portafolio.entity.GrupoIntegrante;
import com.didactica.portafolio.entity.GrupoInfo;
import com.didactica.portafolio.exception.BadRequestException;
import com.didactica.portafolio.mapper.GrupoInfoMapper;
import com.didactica.portafolio.repository.GrupoInfoRepository;
import com.didactica.portafolio.service.interfaces.CloudinaryService;
import com.didactica.portafolio.service.interfaces.GrupoInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class GrupoInfoServiceImpl implements GrupoInfoService {
    private static final String DEFAULT_IMAGE = "https://images.unsplash.com/photo-1529156069898-49953e39b3ac?auto=format&fit=crop&w=1000&q=80";

    private final GrupoInfoRepository repository;
    private final GrupoInfoMapper mapper;
    private final CloudinaryService cloudinaryService;

    public GrupoInfoServiceImpl(GrupoInfoRepository repository, GrupoInfoMapper mapper, CloudinaryService cloudinaryService) {
        this.repository = repository;
        this.mapper = mapper;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    @Transactional
    public GrupoInfoResponse getPublicGrupoInfo() {
        var grupoInfo = repository.findFirstByActivoTrueOrderByIdAsc()
                .orElseGet(this::createDefault);
        return mapper.toResponse(grupoInfo);
    }

    @Override
    @Transactional
    public GrupoInfoResponse getAdminGrupoInfo() {
        var grupoInfo = repository.findFirstByOrderByIdAsc()
                .orElseGet(this::createDefault);
        return mapper.toResponse(grupoInfo);
    }

    @Override
    @Transactional
    public GrupoInfoResponse update(GrupoInfoRequest request) {
        var grupoInfo = repository.findFirstByOrderByIdAsc()
                .orElseGet(this::createDefault);
        mapper.update(grupoInfo, request);
        return mapper.toResponse(repository.save(grupoInfo));
    }

    @Override
    @Transactional
    public GrupoInfoResponse uploadImage(MultipartFile archivo) {
        var grupoInfo = repository.findFirstByOrderByIdAsc()
                .orElseGet(this::createDefault);
        var upload = cloudinaryService.upload(archivo, "portafolio-didactica/grupo");
        grupoInfo.setImagenUrl(upload.getUrl());
        return mapper.toResponse(repository.save(grupoInfo));
    }

    @Override
    @Transactional
    public GrupoInfoResponse uploadMemberImage(Integer index, MultipartFile archivo) {
        var grupoInfo = repository.findFirstByOrderByIdAsc()
                .orElseGet(this::createDefault);
        if (index == null || index < 0 || index >= grupoInfo.getIntegrantes().size()) {
            throw new BadRequestException("Integrante no encontrado");
        }
        var upload = cloudinaryService.upload(archivo, "portafolio-didactica/integrantes");
        grupoInfo.getIntegrantes().get(index).setImagenUrl(upload.getUrl());
        return mapper.toResponse(repository.save(grupoInfo));
    }

    private GrupoInfo createDefault() {
        var grupoInfo = GrupoInfo.builder()
                .titulo("¿Quiénes somos?")
                .descripcion("Equipo de trabajo del portafolio de Didáctica.")
                .imagenUrl(DEFAULT_IMAGE)
                .integrantes(List.of(
                        defaultMember("Leonardo"),
                        defaultMember("Oveimar"),
                        defaultMember("Maria"),
                        defaultMember("Veronica")
                ))
                .activo(true)
                .build();
        return repository.save(grupoInfo);
    }

    private GrupoIntegrante defaultMember(String nombre) {
        return GrupoIntegrante.builder()
                .nombre(nombre)
                .descripcion("Integrante del equipo de trabajo del portafolio de Didáctica.")
                .imagenUrl(DEFAULT_IMAGE)
                .build();
    }
}
