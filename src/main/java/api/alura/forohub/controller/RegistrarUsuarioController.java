package api.alura.forohub.controller;

import api.alura.forohub.dto.RegistrarUsuarioDTO;
import api.alura.forohub.dto.UserResponseDTO;
import api.alura.forohub.model.Usuario;
import api.alura.forohub.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/registro")
public class RegistrarUsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping
    @Transactional
    public ResponseEntity registrarUsuario(@RequestBody @Valid RegistrarUsuarioDTO registrarUsuarioDTO, UriComponentsBuilder uriComponentsBuilder) {
        try {
            Usuario usuario = usuarioRepository.save(new Usuario(registrarUsuarioDTO, bCryptPasswordEncoder));
            UserResponseDTO userResponseDTO = new UserResponseDTO(
                    usuario.getId(), usuario.getUsername()
            );
            URI url = uriComponentsBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
            return ResponseEntity.created(url).body(userResponseDTO);
        } catch (ConstraintViolationException ex) {
            return ResponseEntity.badRequest().body("Validacion fallida");
        }
    }
}
