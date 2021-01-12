package br.ufscar.dc.dsw.promonstraomvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.ufscar.dc.dsw.promonstraomvc.domain.Sale;
import br.ufscar.dc.dsw.promonstraomvc.service.spec.ISaleService;
import net.bytebuddy.asm.Advice.This;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class PromocaoRestController {

    @Autowired
    private ISaleService service;

    @GetMapping(path = "/promocoes")
    public ResponseEntity<List<Sale>> lista() {
        List<Sale> lista = service.findAll();
        if (lista.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping(path = "/promocoes/{id}")
    public ResponseEntity<Sale> lista(@PathVariable("id") Long id) {
        Sale sale = service.findById(id).get();
        if (sale == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sale);
    }

    @GetMapping(path = "/promocoes/sites/{id}")
    public ResponseEntity<List<Sale>> listaPorTituloSite(@PathVariable("id") Long id) {
        List<Sale> lista = service.findAllByWebsite(id);
        if (lista.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping(path = "/promocoes/teatros/{id}")
    public ResponseEntity<List<Sale>> listaPorTituloTheat(@PathVariable("id") Long id) {
        List<Sale> lista = service.findAllByTheater(id);
        if (lista.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lista);
    }

}