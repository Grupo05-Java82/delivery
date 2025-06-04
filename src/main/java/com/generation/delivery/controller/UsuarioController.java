package com.generation.delivery.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.delivery.model.Usuario;
import com.generation.delivery.model.UsuarioLogin;
import com.generation.delivery.repository.UsuarioRepository;
import com.generation.delivery.security.JwtService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtService jwtService;
	
	// BUSCAR TODOS OS USUARIOS REGISTRADOS
	@GetMapping("/usuarios")
	public ResponseEntity<List<Usuario>> getAll(){
		return ResponseEntity.ok(usuarioRepository.findAll());
	}
	
	// BUSCAR USUARIO POR ID
	@GetMapping("/{id}")
	public ResponseEntity <Usuario> getById (@PathVariable Long id){
		return usuarioRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	// BUSCAR USUARIO POR NOME
	@GetMapping("/nome/{nome}")
	public ResponseEntity <Optional<Usuario>> getByNome(@PathVariable String nome){
		// A consulta já retorna uma lista vazia se não encontrar, não precisa verificar isEmpty e retornar NOT_FOUND
		return ResponseEntity.ok(usuarioRepository.findAllByNomeContainingIgnoreCase(nome));
	}

	// BUSCAR USUARIO POR EMAIL (novo método de busca)
	@GetMapping("/usuario/{usuario}") // O nome do PathVariable reflete o campo 'usuario' que é o email
	public ResponseEntity<Usuario> getByUsuario(@PathVariable String usuario){
		return usuarioRepository.findByUsuario(usuario)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
		
	// CADASTRAR UM USUARIO
	@PostMapping("/usuarios/cadastrar")
	public ResponseEntity<?> post(@Valid @RequestBody Usuario usuario) {
		if(usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent()) {
			 ResponseEntity.badRequest();
		}
		
		usuario.setSenha(criptografarSenha(usuario.getSenha()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepository.save(usuario));
	}
	
	// ATUALIZAR O USUARIO ESCOLHIDO
	@PutMapping("/usuarios/atualizar")
	public ResponseEntity<Usuario> put(@Valid @RequestBody Usuario usuario) {
		// Verifica se o ID do usuário existe para atualização
		if (usuario.getId() == null || !usuarioRepository.existsById(usuario.getId())) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); 
		}

		// Verifica se o e-mail já está em uso por OUTRO usuário
		Optional<Usuario> usuarioComMesmoEmail = usuarioRepository.findByUsuario(usuario.getUsuario());
		if (usuarioComMesmoEmail.isPresent() && !usuarioComMesmoEmail.get().getId().equals(usuario.getId())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro: Este e-mail já está cadastrado para outro usuário!");
		}
		
		// Se o ID existe e o e-mail é válido (não em uso por outro), atualiza
		return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.save(usuario));
	}


	@PostMapping("/usuarios/logar")
	public ResponseEntity <UsuarioLogin> autenticarUsuario(@Valid @RequestBody Optional<UsuarioLogin> usuarioLogin ) {
		System.out.println("chegou aki");
		var credenciais = new UsernamePasswordAuthenticationToken(usuarioLogin.get().getUsuario(),
				usuarioLogin.get().getSenha());

		Authentication authentication = authenticationManager.authenticate(credenciais);

		if (authentication.isAuthenticated()) {

			Optional<Usuario> usuario = usuarioRepository.findByUsuario(usuarioLogin.get().getUsuario());

			if (usuario.isPresent()) {

				usuarioLogin.get().setId(usuario.get().getId());
				usuarioLogin.get().setNome(usuario.get().getNome());
				usuarioLogin.get().setFoto(usuario.get().getFoto());
				usuarioLogin.get().setSenha("");
				usuarioLogin.get().setToken(gerarToken(usuarioLogin.get().getUsuario()));
				
				System.out.println(usuarioLogin.get());
				return ResponseEntity.ok().body(usuarioLogin.get());
				
			}
		}

		return ResponseEntity.notFound().build();
	}
	
	private String gerarToken(String usuario) {
		return "Bearer " + jwtService.generateToken(usuario);
	}
	
	public String criptografarSenha(String senha) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(senha);
	}
	
}
	
	// ***** O MÉTODO DELETE PARA USUARIO FOI REMOVIDO/COMENTADO CONFORME REQUISITO DO PROFESSOR *****
	/*
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		if(usuario.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!");
		
		usuarioRepository.deleteById(id);
	}
	*/
	
