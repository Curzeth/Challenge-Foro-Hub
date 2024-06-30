package api.alura.forohub.controller;

import api.alura.forohub.dto.JWTTokenDTO;
import api.alura.forohub.dto.UsuarioDTO;
import api.alura.forohub.model.Usuario;
import api.alura.forohub.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity usuarioAuthentication(@RequestBody @Valid UsuarioDTO usuarioDTO) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(usuarioDTO.username(), usuarioDTO.password());
        var usuarioAuth = authenticationManager.authenticate(authToken);
        var token = tokenService.generateToken((Usuario) usuarioAuth.getPrincipal());
        return ResponseEntity.ok(new JWTTokenDTO(token));
    }
}
