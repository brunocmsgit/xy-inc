package br.com.xyinc.pontointeresse.daos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.xyinc.pontointeresse.models.Filtro;
import br.com.xyinc.pontointeresse.models.PontoInteresse;

@Repository
public class PontoInteresseDao {

	@PersistenceContext
	private EntityManager manager;

	public List<PontoInteresse> all() {
		return manager.createQuery("select p from PontoInteresse p", PontoInteresse.class).getResultList();
	}

	public void save(PontoInteresse pontoInteresse) throws Exception {
		
		if (pontoInteresse!= null && (pontoInteresse.getCoordenadaX() <= 0 || pontoInteresse.getCoordenadaY() <= 0)) {
			throw new Exception("Coordenada Negativa");
		}
		
		manager.persist(pontoInteresse);
	}

	public PontoInteresse findById(Integer id) {
		return manager.find(PontoInteresse.class, id);
	}

	public void remove(PontoInteresse pontoInteresse) {
		manager.remove(pontoInteresse);
	}

	public void update(PontoInteresse pontoInteresse) throws Exception {
		
		if (pontoInteresse!= null && (pontoInteresse.getCoordenadaX() <= 0 || pontoInteresse.getCoordenadaY() <= 0)) {
			throw new Exception("Coordenada Negativa");
		}
		
		manager.merge(pontoInteresse);
	}
	
	public List<PontoInteresse> filtrar(Filtro filtro) {
		
		StringBuilder query = new StringBuilder("select p from PontoInteresse p");
				
		TypedQuery<PontoInteresse> listaTodos = manager.createQuery(query.toString(), PontoInteresse.class);
		
		if (filtro != null && filtro.getX() != null && filtro.getY() != null && filtro.getdMax() != null) {
			
			List<PontoInteresse> listaPontos = new ArrayList<>();
			
			for(PontoInteresse ponto : listaTodos.getResultList()) {
				
				double distanciaX = (filtro.getX() - ponto.getCoordenadaX());
				double distanciaY = (filtro.getY() - ponto.getCoordenadaY());
				
				if (Math.hypot(distanciaX, distanciaY) <= filtro.getdMax()) {
					listaPontos.add(ponto);
				}
			}
			
			return listaPontos;
		}
				
		return listaTodos.getResultList();
	}

}
