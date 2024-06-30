package api.alura.forohub.dto;

import api.alura.forohub.model.Status;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TopicoActualizadoDTO(
        @NotNull Long id,
        String titulo,
        String mensaje,
        Status stauts,
        @NotNull Long usuario_Id,
        String curso,
        LocalDateTime date
) {
}
