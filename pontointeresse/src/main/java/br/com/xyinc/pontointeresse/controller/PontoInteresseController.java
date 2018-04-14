package br.com.xyinc.pontointeresse.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.xyinc.pontointeresse.model.Filtro;
import br.com.xyinc.pontointeresse.model.PontoInteresse;
import br.com.xyinc.pontointeresse.repository.PontoInteresseRepository;
import br.com.xyinc.pontointeresse.service.PontoInteresseService;

@Controller
@RequestMapping("/poi")
@Transactional
public class PontoInteresseController {

	@Autowired
	private PontoInteresseService pontoInteresseService;
	
	@Autowired
	private PontoInteresseRepository repository;

	@RequestMapping("/form")
	public ModelAndView form(PontoInteresse pontoInteresse) {
		ModelAndView modelAndView = new ModelAndView("poi/form-add");
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView save(@Valid PontoInteresse pontoInteresse, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return form(pontoInteresse);
		}
		try {
			repository.save(pontoInteresse);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/poi");
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ModelAndView load(@PathVariable("id") Integer id) {
		ModelAndView modelAndView = new ModelAndView("poi/form-update");
		modelAndView.addObject("poi", repository.findOne(id));
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(defaultValue = "0", required = false) int page, @ModelAttribute Filtro filtro) {
		ModelAndView modelAndView = new ModelAndView("poi/list");
		modelAndView.addObject("listaPois", pontoInteresseService.filtrar(filtro));
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/remove/{id}")
	public String remove(@PathVariable("id") Integer id) {
		PontoInteresse pontoInteresse = repository.findOne(id);
		repository.delete(pontoInteresse);
		return "redirect:/poi";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/{id}")
	public ModelAndView update(@PathVariable("id") Integer id, @Valid PontoInteresse pontoInteresse, BindingResult bindingResult) {
		pontoInteresse.setId(id);
		if (bindingResult.hasErrors()) {
			return new ModelAndView("poi/form-update");
		}
		try {
			repository.save(pontoInteresse);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/poi");
	}
	
}
