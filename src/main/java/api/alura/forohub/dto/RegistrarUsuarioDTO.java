package api.alura.forohub.dto;

import jakarta.validation.constraints.NotBlank;

public record RegistrarUsuarioDTO(
        @NotBlank
        String username,
        @NotBlank
        String password
) {
}
