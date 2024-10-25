package com.api.cocina.recetas.dto.errors;

import java.util.List;
import java.util.Map;

public record GenericError(String mensaje, List<Map<String, String>> errores) {

}
