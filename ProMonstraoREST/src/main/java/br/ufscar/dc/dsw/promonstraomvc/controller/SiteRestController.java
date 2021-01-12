package br.ufscar.dc.dsw.promonstraomvc.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufscar.dc.dsw.promonstraomvc.domain.City;
import br.ufscar.dc.dsw.promonstraomvc.domain.Theater;
import br.ufscar.dc.dsw.promonstraomvc.domain.Website;
import br.ufscar.dc.dsw.promonstraomvc.service.spec.IWebsiteService;

@RestController
public class SiteRestController {
	
	@Autowired
	private IWebsiteService service;

	private boolean isJSONValid(String jsonInString) {
		try {
			return new ObjectMapper().readTree(jsonInString) != null;
		} catch (IOException e) {
			return false;
		}
	}
	
	/*
		{
			"email": "site@monstro.com",
			"password": "senha",
			"name": "Site Monstro",
			"phone_number": "(16)99090-7070",
			"url": "https://sitemonstro.com"
		}
	*/
	
	private void parse(Website website, JSONObject json) {

		Object id = json.get("id");
		if (id != null) {
			if (id instanceof Integer) {
				website.setId(((Integer) id).longValue());
			} else {
				website.setId(((Long) id));
			}
		}

		website.setEmail((String) json.get("email"));
		website.setPassword((String) json.get("password"));
		website.setName((String) json.get("name"));
		website.setPhoneNumber((String) json.get("phone_number"));
		website.setUrl((String) json.get("url"));
	}

	@GetMapping(path = "/sites")
	public ResponseEntity<List<Website>> lista() {
		List<Website> lista = service.findAll();
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}

	@GetMapping(path = "/sites/{id}")
	public ResponseEntity<Website> lista(@PathVariable("id") long id) {
		Website website = service.findById(id).get();
		if (website == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(website);
    }
    

	@PostMapping(path = "/sites")
	@ResponseBody
	public ResponseEntity<Website> cria(@RequestBody JSONObject json) {
		try {
			if (isJSONValid(json.toString())) {
				Website website = new Website();
				parse(website, json);
				service.save(website);
				System.out.println("TOAQ");
				return ResponseEntity.ok(website);
			} else {
				System.out.println("TOAQ2");
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			System.out.println("TOAQ3");
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}

	@PutMapping(path = "/sites/{id}")
	public ResponseEntity<Website> atualiza(@PathVariable("id") long id, @RequestBody JSONObject json) {
		try {
			if (isJSONValid(json.toString())) {
				Website website = service.findById(id).get();
				if (website == null) {
					return ResponseEntity.notFound().build();
				} else {
					parse(website, json);
					service.save(website);
					return ResponseEntity.ok(website);
				}
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}
	

	@DeleteMapping(path = "/sites/{id}")
	public ResponseEntity<Boolean> remove(@PathVariable("id") long id) {

		Website website = service.findById(id).get();
		if (website == null) {
			return ResponseEntity.notFound().build();
		} else {
			service.deleteById(id);
			return ResponseEntity.noContent().build();
		}
	}
		
}
