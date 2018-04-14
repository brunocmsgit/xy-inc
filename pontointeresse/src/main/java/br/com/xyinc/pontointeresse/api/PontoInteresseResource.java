package br.com.xyinc.pontointeresse.api;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.xyinc.pontointeresse.model.Filtro;
import br.com.xyinc.pontointeresse.model.PontoInteresse;
import br.com.xyinc.pontointeresse.repository.PontoInteresseRepository;
import br.com.xyinc.pontointeresse.service.PontoInteresseService;

@RestController
@RequestMapping("/pontoInteresses")
public class PontoInteresseResource {

	@Autowired
	private PontoInteresseService pontoInteresseService;

	@Autowired
	private PontoInteresseRepository repository;

	@GetMapping
	public ResponseEntity<Collection<PontoInteresse>> buscarTodosPontos() {
		List<PontoInteresse> pontos = repository.findAll();
		return ResponseEntity.ok(pontos);
	}

	@PostMapping
	public ResponseEntity<PontoInteresse> cadastrar(@Valid @RequestBody PontoInteresse pontoInteresse) {
		repository.save(pontoInteresse);
		return ResponseEntity.status(HttpStatus.CREATED).body(pontoInteresse);
	}

	@PostMapping("/filtrar")
	public ResponseEntity<Collection<PontoInteresse>> filtrar(@RequestBody Filtro filtro) {
		List<PontoInteresse> pontos = pontoInteresseService.filtrar(filtro);
		return ResponseEntity.ok(pontos);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void excluir(@PathVariable Integer id) {
		repository.delete(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PontoInteresse> alterar(@PathVariable Integer id, @Valid @RequestBody PontoInteresse pontoInteresse) {
		PontoInteresse pontoInteresseSalvo = pontoInteresseService.atualizar(id, pontoInteresse);
		return ResponseEntity.status(HttpStatus.OK).body(pontoInteresseSalvo);
	}

}
