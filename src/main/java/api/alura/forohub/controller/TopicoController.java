package api.alura.forohub.controller;

import api.alura.forohub.dto.ResponseDTO;
import api.alura.forohub.dto.TopicoActualizadoDTO;
import api.alura.forohub.dto.TopicoDTO;
import api.alura.forohub.dto.TopicosListadoDTO;
import api.alura.forohub.errores.IntegrityValidation;
import api.alura.forohub.model.Topico;
import api.alura.forohub.repository.TopicoRepository;
import api.alura.forohub.repository.UsuarioRepository;
import api.alura.forohub.service.TopicoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseBody
@RequestMapping("/topico")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TopicoService topicoService;

    @PostMapping("/topico")
    @Transactional
    public ResponseEntity topicoRegistrado(@RequestBody @Valid TopicoDTO topicoDTO) throws IntegrityValidation {
        var topicoRegistrado = topicoService.topicoCreado(topicoDTO);
        return ResponseEntity.ok(topicoRegistrado);
    }

    @GetMapping("/topicos")
    public ResponseEntity<Page<TopicosListadoDTO>> listarTopicos(@PageableDefault(size = 10)Pageable pageable) {
        return ResponseEntity.ok(topicoRepository.findByActiveTrue(pageable).map(TopicosListadoDTO::new));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity topicoActualizado(@RequestBody @Valid TopicoActualizadoDTO topicoActualizadoDTO) {
        Topico topico = topicoRepository.getReferenceById(topicoActualizadoDTO.id());
        topico.topicoActualizado(topicoActualizadoDTO);
        return ResponseEntity.ok(new ResponseDTO(topico.getId(),
                topico.getTitulo(), topico.getMensaje(),
                topico.getStatus(), topico.getAutor().getId(),
                topico.getCurso(), topico.getFecha()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity borrarTopico(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        topico.desactivarTopico();
        return ResponseEntity.noContent().build();
    }
}
