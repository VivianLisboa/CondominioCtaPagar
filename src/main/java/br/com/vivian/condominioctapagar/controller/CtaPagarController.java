package br.com.vivian.condominioctapagar.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vivian.condominioctapagar.dto.CtaPagarDTO;
import br.com.vivian.condominioctapagar.service.CtaPagarService;

@RestController
@RequestMapping(value ="vivi")
public class CtaPagarController {
	public CtaPagarService ctaPagarService;
	
	@Autowired
	public CtaPagarController(CtaPagarService ctaPagarService) {
		this.ctaPagarService = ctaPagarService;
	}
	@GetMapping(value = "/cta_pagar")
	public ResponseEntity<List<CtaPagarDTO>>obterCtaPagar(){
		List<CtaPagarDTO> ctaPagar = ctaPagarService.findAll();
		return new ResponseEntity<List<CtaPagarDTO>>(ctaPagar,HttpStatus.OK);
	}
	@PostMapping(value = "/cta_pagar")
	public ResponseEntity<?>salvar(@RequestBody @Valid CtaPagarDTO ctaPagarDTO){
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@PutMapping(value = "/cta_pagar/{id}")
	public ResponseEntity<?>update(@RequestBody CtaPagarDTO ctaPagarDTO){
		this.ctaPagarService.update(ctaPagarDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@DeleteMapping(value = "/cta_pagar/{id}")
	public ResponseEntity<?>delete(@PathVariable("id")Integer id){
		this.ctaPagarService.findById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
