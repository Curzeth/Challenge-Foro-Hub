package api.alura.forohub.dto;

import jakarta.validation.constraints.NotBlank;

public record UsuarioDTO(
        @NotBlank
        String username,
        @NotBlank
        String password
) {
}
