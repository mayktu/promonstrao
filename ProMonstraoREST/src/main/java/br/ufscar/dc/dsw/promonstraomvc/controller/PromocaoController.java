package br.ufscar.dc.dsw.promonstraomvc.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.promonstraomvc.security.*;

import br.ufscar.dc.dsw.promonstraomvc.domain.Sale;
import br.ufscar.dc.dsw.promonstraomvc.domain.Theater;
import br.ufscar.dc.dsw.promonstraomvc.service.spec.ITheaterService;
import br.ufscar.dc.dsw.promonstraomvc.domain.User;
import br.ufscar.dc.dsw.promonstraomvc.service.spec.ISaleService;
import net.bytebuddy.asm.Advice.This;
import br.ufscar.dc.dsw.promonstraomvc.domain.Website;
import br.ufscar.dc.dsw.promonstraomvc.service.spec.IWebsiteService;

@Controller
@RequestMapping("/promocoes")
public class PromocaoController {
	
	@Autowired
	private ISaleService saleService;

	@Autowired
	private IWebsiteService websiteService;
	
	@Autowired
	private ITheaterService theaterService;

	@GetMapping("/cadastrar")
	public String cadastrar(Sale sale) {	
		sale.setTheater(theaterService.findById(this.getUser().getId()).orElse(null));
		return "promocao/cadastro";
	}
	
	private User getUser() {
		UsuarioDetails usuarioDetails = (UsuarioDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return usuarioDetails.getUser();
	}

	@GetMapping("/listar")
	public String listar(ModelMap model) {
		
		User u = this.getUser();
		
		if(u.getRole().equals("THEATER")) {			
			model.addAttribute("promotions", saleService.findAllByTheater(u.getId()));
		}
		if(u.getRole().equals("WEBSITE")) {
			model.addAttribute("promotions", saleService.findAllByWebsite(u.getId()));
		}
		
		return "promocao/lista";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Sale sale, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "promocao/cadastro";
		}
		
		sale.setTheater(theaterService.findById(this.getUser().getId()).orElse(null));

		saleService.save(sale);
		attr.addFlashAttribute("sucess", "Promocao inserida com sucesso");
		return "redirect:/promocoes/listar";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		saleService.findById(id).ifPresent(o -> model.addAttribute("sale", o));
		//model.addAttribute("promocao", saleService.findById(id));
		return "promocao/cadastro";
	}

	@PostMapping("/editar")
	public String editar(@Valid Sale sale, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "promocao/cadastro";
		}
		
		sale.setTheater(theaterService.findById(this.getUser().getId()).orElse(null));
		
		saleService.save(sale);
		attr.addFlashAttribute("sucess", "Promocao editada com sucesso.");
		return "redirect:/promocoes/listar";
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		saleService.deleteById(id);
		attr.addFlashAttribute("sucess", "Promocao excluída com sucesso.");
		return "redirect:/promocoes/listar";
	}

	@ModelAttribute("websites")
    public List<Website> listaSites() {
        return websiteService.findAll();
    }

}
