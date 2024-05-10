package com.projetopipocagil.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller
public class UsuarioControles {
	
	@RequestMapping
	public String controle() {
		return "controle";
		
	}
	
	
}












































/*
 * @RestController // RECURSO WEB QUE É IMPLEMENTADO POR UM CONTROLADOR REST
 * 
 * @RequestMapping("usuarios") // NOME DO RECURSO public class UsuarioControles
 * {
 * 
 * @Autowired private ServicoUsuario servicoUser;
 * 
 * @GetMapping public ResponseEntity<List<Usuario>> listar(){ List<Usuario>
 * usuarios = servicoUser.findAll();
 * 
 * return ResponseEntity.ok().body(usuarios); }
 * 
 * @GetMapping( "/{usuarioId}") public ResponseEntity<Usuario>
 * buscar(@PathVariable Long usuarioId) throws ServicoExc { Usuario usuario =
 * servicoUser.findById(usuarioId);
 * 
 * return ResponseEntity.ok().body(usuario); }
 * 
 * @PutMapping( "/{usuarioId}") public ResponseEntity<Usuario>
 * atualizar(@PathVariable Long usuarioId, @RequestBody Usuario
 * usuarioAtualizado) throws ServicoExc { Usuario usuario =
 * servicoUser.update(usuarioId, usuarioAtualizado);
 * 
 * return ResponseEntity.ok().body(usuario); }
 * 
 * @PostMapping public ResponseEntity<Usuario> adicionar(@RequestBody Usuario
 * usuarioNovo) { Usuario usuario = servicoUser.create(usuarioNovo); URI uri =
 * ServletUriComponentsBuilder .fromCurrentRequest() .path("/{usuarioId}")
 * .buildAndExpand(usuario.getId()) .toUri(); return
 * ResponseEntity.created(uri).build(); }
 * 
 * @DeleteMapping( "/{usuarioId}") public ResponseEntity<Usuario>
 * apagar(@PathVariable Long usuarioId) throws ServicoExc {
 * servicoUser.delete(usuarioId); return ResponseEntity.noContent().build(); }
 * 
 * 
 * 
 * // @GetMapping // public ResponseEntity<Usuario> findAll(){ // Usuario u =
 * new Usuario(null, "JoãoSilva", "joaosilva", "senha123", "joao@email.com"); //
 * return ResponseEntity.ok().body(u); // }
 */
