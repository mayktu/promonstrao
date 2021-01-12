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
import br.ufscar.dc.dsw.promonstraomvc.domain.User;
import br.ufscar.dc.dsw.promonstraomvc.service.spec.ITheaterService;

@RestController
public class TeatroRestController {
	
	@Autowired
	private ITheaterService service;

	private boolean isJSONValid(String jsonInString) {
		try {
			return new ObjectMapper().readTree(jsonInString) != null;
		} catch (IOException e) {
			return false;
		}
	}
	
	/*
	 	{
			"email": "teatro@monstro.com",
			"password": "senha",
			"name": "Teatro Monstro",
			"cnpj": "0919191919/10",
			"city": { "id": 3, "name": "Batatais" }
		}
	 */
	
	private void parse(City city, JSONObject json) {
		
		@SuppressWarnings("unchecked")
		Map<String, Object> map = (Map<String, Object>) json.get("city");

		Object id = map.get("id");
		if (id instanceof Integer) {
			city.setId(((Integer) id).longValue());
		} else {
			city.setId(((Long) id));
		}

		city.setName((String) json.get("name"));
	}

	private void parse(Theater theater, JSONObject json) {

		Object id = json.get("id");
		if (id != null) {
			if (id instanceof Integer) {
				theater.setId(((Integer) id).longValue());
			} else {
				theater.setId(((Long) id));
			}
		}

		theater.setEmail((String) json.get("email"));
		theater.setPassword((String) json.get("password"));
		theater.setName((String) json.get("name"));
		theater.setCnpj((String) json.get("cnpj"));
		City city = new City();
		parse(city, json);
		theater.setCity(city);
	}

	@GetMapping(path = "/teatros")
	public ResponseEntity<List<Theater>> lista() {
		List<Theater> lista = service.findAll();
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}

	@GetMapping(path = "/teatros/{id}")
	public ResponseEntity<Theater> lista(@PathVariable("id") long id) {
		Theater theater = service.findById(id).get();
		if (theater == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(theater);
	}

	@GetMapping(path = "/teatros/cidades/{city}")
	public ResponseEntity<List<Theater>> listaByCity(@PathVariable("city") String city) {
		List<Theater> lista = service.findAllByCity(city).get();
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}
	
	@PostMapping(path = "/teatros")
	@ResponseBody
	public ResponseEntity<Theater> cria(@RequestBody JSONObject json) {
		try {
			if (isJSONValid(json.toString())) {
				Theater theater = new Theater();
				parse(theater, json);
				service.save(theater);				
				return ResponseEntity.ok(theater);
			} else {				
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}

	@PutMapping(path = "/teatros/{id}")
	public ResponseEntity<Theater> atualiza(@PathVariable("id") long id, @RequestBody JSONObject json) {
		try {
			if (isJSONValid(json.toString())) {
				Theater theater = service.findById(id).get();
				if (theater == null) {
					return ResponseEntity.notFound().build();
				} else {
					parse(theater, json);
					service.save(theater);
					return ResponseEntity.ok(theater);
				}
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}
	

	@DeleteMapping(path = "/teatros/{id}")
	public ResponseEntity<Boolean> remove(@PathVariable("id") long id) {

		Theater theater = service.findById(id).get();
		if (theater == null) {
			return ResponseEntity.notFound().build();
		} else {
			service.deleteById(id);
			return ResponseEntity.noContent().build();
		}
	}
		
}
