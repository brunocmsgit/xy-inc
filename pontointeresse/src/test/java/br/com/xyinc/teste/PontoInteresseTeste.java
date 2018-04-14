package br.com.xyinc.teste;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.xyinc.pontointeresse.Boot;
import br.com.xyinc.pontointeresse.model.Filtro;
import br.com.xyinc.pontointeresse.model.PontoInteresse;
import br.com.xyinc.pontointeresse.repository.PontoInteresseRepository;
import br.com.xyinc.pontointeresse.service.PontoInteresseService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes=Boot.class)
@Transactional
public class PontoInteresseTeste {

	@Autowired
	private PontoInteresseRepository respository;
	
	@Autowired
	private PontoInteresseService pontoInteresseService;

	@Test
	@Rollback(true)
	public void testeSalvar() {
		PontoInteresse poi = new PontoInteresse();
		poi.setNome("Ponto de Interesse OK");
		poi.setCoordenadaX(1);
		poi.setCoordenadaY(2);

		try {
			respository.save(poi);
		} catch (Exception ex) {
			Assert.fail("Não é possível salvar ponto de interesse");
		}
	}	
	
	@Test
	@Rollback(true)
    public void testeSalvarCoordenadaNegativa() {
		PontoInteresse poi = new PontoInteresse();
		poi.setNome("Ponto de Interesse Coordenada Negativa");
		poi.setCoordenadaX(-10);
		poi.setCoordenadaY(-5);
	
		try {
			respository.save(poi);
		    Assert.fail("Não é possível salvar ponto de interesse com coordenada negativa");
	    
		} catch (Exception ex) {
			Assert.assertTrue(ex instanceof ConstraintViolationException);
		}
    }
	
	@Test
    public void testePoiIdUm() {
		
		//Mesmo id e dados do banco, cadastrado conforme exemplo das instruções do PDF
		PontoInteresse poi = new PontoInteresse();
		poi.setId(1);
		poi.setNome("Lanchonete");
		poi.setCoordenadaX(27);
		poi.setCoordenadaY(12);
						
		PontoInteresse pontoInteresseUm = respository.findOne(1);
		
		Assert.assertTrue(poi.equals(pontoInteresseUm));
    }
	
	@Test
    public void testeListaCompleta() {
		
		int numeroDePois = 7; // Baseado na quantidade do exemplo do PDF
								
		List<PontoInteresse> pontos = respository.findAll();
		
		Assert.assertEquals(numeroDePois, pontos.size());
    }
	
	@Test
    public void testeProximidade() {
				
		int numeroDePois = 4; // Baseado na quantidade Filtrada pelas coordenadas e distancia maxima do exemplo do PDF
		
		Filtro filtro = new Filtro(20, 10, 10);
								
		List<PontoInteresse> pontos = pontoInteresseService.filtrar(filtro);
		
		Assert.assertEquals(numeroDePois, pontos.size());
    }
	
	
}
