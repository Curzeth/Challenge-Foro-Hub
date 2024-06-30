package api.alura.forohub.dto;

import api.alura.forohub.model.Status;
import api.alura.forohub.model.Topico;

import java.time.LocalDateTime;

public record ResponseDTO(
        Long id,
        String titulo,
        String mensaje,
        Status status,
        Long usuario_Id,
        String curso,
        LocalDateTime fecha) {
    public ResponseDTO(Topico topicoId) {
        this(
                topicoId.getId(),
                topicoId.getTitulo(),
                topicoId.getMensaje(),
                topicoId.getStatus(),
                topicoId.getAutor().getId(),
                topicoId.getCurso(),
                topicoId.getFecha());
    }
}
