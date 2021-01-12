package br.ufscar.dc.dsw.promonstraomvc.controller;

import java.util.List;
import java.util.Optional;

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

import br.ufscar.dc.dsw.promonstraomvc.domain.Website;
import br.ufscar.dc.dsw.promonstraomvc.service.spec.IWebsiteService;

@Controller
@RequestMapping("/sites")
public class SiteController {
	
	@Autowired
	private IWebsiteService websiteService;

	@GetMapping("/cadastrar")
	public String cadastrar(Website website) {
		return "site/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("sites", websiteService.findAll());
		return "site/lista";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Website website, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "site/cadastro";
		}

		websiteService.save(website);
		attr.addFlashAttribute("sucess", "Site inserido com sucesso");
		return "redirect:/sites/listar";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		websiteService.findById(id).ifPresent(o -> model.addAttribute("website", o));
		//model.addAttribute("website", websiteService.findById(id));
		return "site/cadastro";
	}

	@PostMapping("/editar")
	public String editar(@Valid Website website, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "site/cadastro";
		}

		websiteService.save(website);
		attr.addFlashAttribute("sucess", "Site editado com sucesso.");
		return "redirect:/sites/listar";
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		websiteService.deleteById(id);
		attr.addFlashAttribute("sucess", "Site excluído com sucesso.");
		return "redirect:/sites/listar";
	}

	@ModelAttribute("sites")
	public List<Website> listaSites() {	
		List<Website> lista = websiteService.findAll();
		return lista;
	}

}