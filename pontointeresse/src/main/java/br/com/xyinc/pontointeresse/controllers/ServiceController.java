package br.com.xyinc.pontointeresse.controllers;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.xyinc.pontointeresse.daos.PontoInteresseDao;
import br.com.xyinc.pontointeresse.models.Filtro;
import br.com.xyinc.pontointeresse.models.PontoInteresse;

@RestController
@Transactional
public class ServiceController {

	@Autowired
	private PontoInteresseDao pontoInteresseDao;

	@RequestMapping(method=RequestMethod.GET, value="/pontoInteresses", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<PontoInteresse>> buscarTodosPontos(){
        List<PontoInteresse> pontos = pontoInteresseDao.all();
        return new ResponseEntity<>(pontos, HttpStatus.OK);
    }
	
	@RequestMapping(method=RequestMethod.POST, value="/pontoInteresses", consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PontoInteresse> cadastrar(@RequestBody PontoInteresse pontoInteresse) throws Exception {
		pontoInteresseDao.save(pontoInteresse);
        return new ResponseEntity<PontoInteresse>(pontoInteresse, HttpStatus.CREATED);
    }
	
	@RequestMapping(method=RequestMethod.POST, value="/pontoInteresses/filtrar", consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<PontoInteresse>> filtrar(@RequestBody Filtro filtro){
		List<PontoInteresse> pontos = pontoInteresseDao.filtrar(filtro);
        return new ResponseEntity<>(pontos, HttpStatus.OK);
    }
	
	@RequestMapping(method=RequestMethod.DELETE, value="/pontoInteresses/{id}")
    public ResponseEntity<PontoInteresse> excluirPonto(@PathVariable Integer id){
		PontoInteresse pontoInteresse = pontoInteresseDao.findById(id);
        if(pontoInteresse==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        pontoInteresseDao.remove(pontoInteresse);
        return new ResponseEntity<PontoInteresse>(HttpStatus.OK);
    }
	
    @RequestMapping(method=RequestMethod.PUT, value="/pontoInteresses", consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PontoInteresse> alterarPonto(@RequestBody PontoInteresse pontoInteresse) throws Exception {
    	pontoInteresseDao.update(pontoInteresse);
        return new ResponseEntity<PontoInteresse>(pontoInteresse, HttpStatus.OK);
    }

}
