package br.ufscar.dc.dsw.promonstraomvc.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.bytebuddy.asm.Advice.This;
import org.springframework.security.core.context.SecurityContextHolder;
import br.ufscar.dc.dsw.promonstraomvc.security.*;

import br.ufscar.dc.dsw.promonstraomvc.domain.Theater;
import br.ufscar.dc.dsw.promonstraomvc.service.spec.ITheaterService;
import br.ufscar.dc.dsw.promonstraomvc.domain.Website;
import br.ufscar.dc.dsw.promonstraomvc.service.spec.IWebsiteService;

import br.ufscar.dc.dsw.promonstraomvc.domain.City;
import br.ufscar.dc.dsw.promonstraomvc.service.spec.ICityService;


@Controller
@RequestMapping("/teatros")
public class TeatroController {
	
	@Autowired
	private ITheaterService theaterService;

	@Autowired
	private IWebsiteService websiteService;

	@Autowired
	private ICityService cityService;

	@GetMapping("/cadastrar")
	public String cadastrar(Theater theater) {
		return "teatro/cadastro";
	}

	
	@GetMapping("/listacidade")
	public String listaCity(Theater theater) {
		return "teatro/listacidade";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("teatros", theaterService.findAll());
		return "teatro/lista";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Theater theater, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "teatro/cadastro";
		}

		theaterService.save(theater);
		attr.addFlashAttribute("sucess", "Teatro inserido com sucesso");
		return "redirect:/teatros/listar";
	}
	
	/*return theaterService.findAllByCity("Sorocaba");*/
	@GetMapping("/listacidade/{name}")
	public String listaTeatrosSelec(@PathVariable("name") String name,ModelMap model) {
		theaterService.findAllByCity(name).ifPresent(o -> model.addAttribute("teatrosID", o));
		return "teatro/listacidade";
	}
	/*
	 * @GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		websiteService.findById(id).ifPresent(o -> model.addAttribute("website", o));
		//model.addAttribute("website", websiteService.findById(id));
		return "site/cadastro";
	}
	 */

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		theaterService.findById(id).ifPresent(o -> model.addAttribute("theater", o));
		//model.addAttribute("teatro", theaterService.findById(id));
		return "teatro/cadastro";
	}

	@PostMapping("/editar")
	public String editar(@Valid Theater theater, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "teatro/cadastro";
		}

		theaterService.save(theater);
		attr.addFlashAttribute("sucess", "Teatro editado com sucesso.");
		return "redirect:/teatros/listar";
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		theaterService.deleteById(id);
		attr.addFlashAttribute("sucess", "Teatro excluído com sucesso.");
		return "redirect:/teatros/listar";
	}

	@ModelAttribute("teatros")
	public List<Theater> listaTeatros() {
		return theaterService.findAll();
	}

	@ModelAttribute("cidades")
    public List<City> listaCidades() {		
        return cityService.buscarTodos();
	}
}
