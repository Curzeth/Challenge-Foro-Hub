package api.alura.forohub.dto;

import api.alura.forohub.model.Status;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TopicoDTO(
        @NotNull String titulo,
        @NotNull String mensaje,
        @NotNull LocalDateTime fecha,
        @NotNull Status status,
        @NotNull Long usuario_Id,
        @NotNull String curso,
        @NotNull Boolean activo
) {}