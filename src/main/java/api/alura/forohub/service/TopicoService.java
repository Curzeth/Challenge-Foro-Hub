package api.alura.forohub.service;

import api.alura.forohub.dto.ResponseDTO;
import api.alura.forohub.dto.TopicoDTO;
import api.alura.forohub.errores.IntegrityValidation;
import api.alura.forohub.model.Topico;
import api.alura.forohub.repository.TopicoRepository;
import api.alura.forohub.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public ResponseDTO topicoCreado(TopicoDTO topicoDTO) {
        if (!usuarioRepository.findById(topicoDTO.usuario_Id()).isPresent()) {
            throw new IntegrityValidation("ID no registrado");
        }
        var titulo = topicoDTO.titulo();
        if (titulo != null && topicoRepository.existsByTituloIgnoreCase(titulo)) {
            throw new IntegrityValidation("Título ya existente");
        }
        String mensaje = topicoDTO.mensaje();
        if (mensaje != null && topicoRepository.existsByMensajeIgnoreCase(mensaje)) {
            throw new IntegrityValidation("Mensaje ya existente");
        }
        var usuario = usuarioRepository.findById(topicoDTO.usuario_Id()).get();
        var topicoId = new Topico(null, titulo, mensaje, topicoDTO.fecha(), topicoDTO.status(), usuario, topicoDTO.curso());
        topicoRepository.save(topicoId);
        return new ResponseDTO(topicoId);
    }
}
