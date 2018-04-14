package br.com.xyinc.pontointeresse.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import br.com.xyinc.pontointeresse.model.Filtro;
import br.com.xyinc.pontointeresse.model.PontoInteresse;
import br.com.xyinc.pontointeresse.repository.PontoInteresseRepository;

@Repository
public class PontoInteresseService {

	@Autowired
	private PontoInteresseRepository repository;
		
	public PontoInteresse atualizar(Integer id, PontoInteresse pontoInteresse) {
		PontoInteresse pontoSalvo = buscarPontoPeloCodigo(id);
		BeanUtils.copyProperties(pontoInteresse, pontoSalvo, "id");
		return repository.save(pontoSalvo);
	}
	
	public PontoInteresse buscarPontoPeloCodigo(Integer id) {
		PontoInteresse pontoSalvo = repository.findOne(id);
		if (pontoSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return pontoSalvo;
	}
	
	public List<PontoInteresse> filtrar(Filtro filtro) {
		
		List<PontoInteresse> listaTodos = repository.findAll();
		
		if (filtro.preenchido()) {
			
			return filtrarPorProximidade(filtro, listaTodos);
		}
				
		return listaTodos;
	}

	private List<PontoInteresse> filtrarPorProximidade(Filtro filtro, List<PontoInteresse> listaTodos) {
		
		List<PontoInteresse> listaPontos = new ArrayList<>();
		
		for(PontoInteresse ponto : listaTodos) {
			
			double distanciaX = (filtro.getX() - ponto.getCoordenadaX());
			double distanciaY = (filtro.getY() - ponto.getCoordenadaY());
			
			if (Math.hypot(distanciaX, distanciaY) <= filtro.getdMax()) {
				listaPontos.add(ponto);
			}
		}
		
		return listaPontos;
	}

}
