package com.didactica.portafolio.service.impl;

import org.springframework.stereotype.Component;

import java.text.Normalizer;
import java.util.Locale;

@Component
class SlugService {
    String from(String value) {
        var normalized = Normalizer.normalize(value, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .toLowerCase(Locale.ROOT)
                .replaceAll("[^a-z0-9]+", "-")
                .replaceAll("(^-|-$)", "");
        return normalized.isBlank() ? "item" : normalized;
    }
}
