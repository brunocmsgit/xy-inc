package br.com.xyinc.pontointeresse.controllers;

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

import br.com.xyinc.pontointeresse.daos.PontoInteresseDao;
import br.com.xyinc.pontointeresse.models.Filtro;
import br.com.xyinc.pontointeresse.models.PontoInteresse;

@Controller
@RequestMapping("/poi")
@Transactional
public class PontoInteresseController {

	@Autowired
	private PontoInteresseDao pontoInteresseDao;

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
			pontoInteresseDao.save(pontoInteresse);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/poi");
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ModelAndView load(@PathVariable("id") Integer id) {
		ModelAndView modelAndView = new ModelAndView("poi/form-update");
		modelAndView.addObject("poi", pontoInteresseDao.findById(id));
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(defaultValue = "0", required = false) int page, @ModelAttribute Filtro filtro) {
		ModelAndView modelAndView = new ModelAndView("poi/list");
		modelAndView.addObject("listaPois", pontoInteresseDao.filtrar(filtro));
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/remove/{id}")
	public String remove(@PathVariable("id") Integer id) {
		PontoInteresse pontoInteresse = pontoInteresseDao.findById(id);
		pontoInteresseDao.remove(pontoInteresse);
		return "redirect:/poi";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/{id}")
	public ModelAndView update(@PathVariable("id") Integer id, @Valid PontoInteresse pontoInteresse, BindingResult bindingResult) {
		pontoInteresse.setId(id);
		if (bindingResult.hasErrors()) {
			return new ModelAndView("poi/form-update");
		}
		try {
			pontoInteresseDao.update(pontoInteresse);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/poi");
	}
	
}
