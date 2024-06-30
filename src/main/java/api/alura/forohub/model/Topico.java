package api.alura.forohub.model;

import api.alura.forohub.dto.TopicoActualizadoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "Topico")
@Table(name = "topicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fecha;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "autor_id")
    private Usuario autor;
    private String curso;
    private boolean active;

    public Topico(Long id, String titulo, String mensaje, LocalDateTime fecha, Status status, Usuario usuario, String curso) {
        this.id = id;
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.fecha = fecha;
        this.fecha = LocalDateTime.now();
        this.status = status;
        this.autor = usuario;
        this.curso = curso;
    }

    public void topicoActualizado(TopicoActualizadoDTO topicoActualizadoDTO) {
        if (topicoActualizadoDTO.titulo() != null) {
            this.titulo = topicoActualizadoDTO.titulo();
        }
        if (topicoActualizadoDTO.mensaje() != null) {
            this.mensaje = topicoActualizadoDTO.mensaje();
        }
        if (topicoActualizadoDTO.stauts() != null) {
            this.curso = topicoActualizadoDTO.curso();
        }
    }

    public void desactivarTopico() {
        this.active = false;
    }
}
