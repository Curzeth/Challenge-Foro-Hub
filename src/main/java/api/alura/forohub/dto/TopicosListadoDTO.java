package api.alura.forohub.dto;

import api.alura.forohub.model.Status;
import api.alura.forohub.model.Topico;

import java.time.LocalDateTime;

public record TopicosListadoDTO(
        Long id,
        String titulo,
        String mensaje,
        Status status,
        Long usuario_Id,
        String curso,
        LocalDateTime fecha
) {
    public TopicosListadoDTO (Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getStatus(),
                topico.getAutor().getId(),
                topico.getCurso(),
                topico.getFecha());
    }
}
