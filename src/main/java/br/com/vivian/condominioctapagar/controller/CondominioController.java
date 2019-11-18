package br.com.vivian.condominioctapagar.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vivian.condominioctapagar.domain.Condominio;
import br.com.vivian.condominioctapagar.dto.CondominioDTO;
import br.com.vivian.condominioctapagar.service.CondominioService;

@RestController
@RequestMapping(value = "vivi")

public class CondominioController {

	public CondominioService condominioService;

	@Autowired
	public CondominioController(CondominioService condominioService) {
		this.condominioService = condominioService;
	}

	@GetMapping(value = "/condominiopg")
	public ResponseEntity<List<CondominioDTO>> obterCondominio() {
		List<CondominioDTO> condominios = condominioService.findAll();
		return new ResponseEntity<List<CondominioDTO>>(condominios, HttpStatus.OK);
	}

	@PostMapping(value = "/condominiopg")
	public ResponseEntity<?> salvar(@RequestBody @Valid CondominioDTO condominioDTO) {
		this.condominioService.save(condominioDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
