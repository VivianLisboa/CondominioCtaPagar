package br.com.vivian.condominioctapagar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import br.com.vivian.condominioctapagar.domain.Condominio;
import br.com.vivian.condominioctapagar.domain.CtaPagar;
import br.com.vivian.condominioctapagar.dto.CtaPagarDTO;

import br.com.vivian.condominioctapagar.repository.CtaPagarRepository;

@Service
public class CtaPagarService {
	private CtaPagarRepository ctaPagarRepository;
	private CondominioService condominioService;

	@Autowired
	public CtaPagarService(CondominioService condominioService, CtaPagarRepository ctaPagarRepository) {
		this.ctaPagarRepository = ctaPagarRepository;
		this.condominioService = condominioService;
	}

	public void save(CtaPagarDTO ctaPagarDTO) {
		Condominio condominio = condominioService.findById(ctaPagarDTO.getCondominio().getId());

		String data = ctaPagarDTO.getData();
		String historico = ctaPagarDTO.getHistorico();
		Double debito = ctaPagarDTO.getDebito();
		Double credito = ctaPagarDTO.getCredito();
		Double saldo = ctaPagarDTO.getSaldo();
		String observacao = ctaPagarDTO.getObservacao();
		String docValido = ctaPagarDTO.getDocValido();
		String comprovPgto = ctaPagarDTO.getComprovPgto();
		String pendente = ctaPagarDTO.getPendente();

		CtaPagar ctaPagar = new CtaPagar(condominio, data, historico, debito, credito, saldo, observacao, docValido,
				comprovPgto, pendente);
		this.ctaPagarRepository.saveAndFlush(ctaPagar);
		ctaPagarDTO.setId(ctaPagar.getId());
	}

	public void update(CtaPagarDTO ctaPagarDTO) {
		Condominio condominio = condominioService.findById(ctaPagarDTO.getId());

		Integer id = ctaPagarDTO.getId();
		String data = ctaPagarDTO.getData();
		String historico = ctaPagarDTO.getHistorico();
		Double debito = ctaPagarDTO.getDebito();
		Double credito = ctaPagarDTO.getCredito();
		Double saldo = ctaPagarDTO.getSaldo();
		String observacao = ctaPagarDTO.getObservacao();
		String docValido = ctaPagarDTO.getDocValido();
		String comprovPgto = ctaPagarDTO.getComprovPgto();
		String pendente = ctaPagarDTO.getPendente();

		CtaPagar ctaPagar = new CtaPagar(id, condominio, data, historico, debito, credito, saldo, observacao, docValido,
				comprovPgto, pendente);
		this.ctaPagarRepository.saveAndFlush(ctaPagar);

	}

	public void deletAll() {
		this.ctaPagarRepository.deleteAll();

	}

	public void deleteById(Integer id) {
		Optional<CtaPagar> ctaPagar = ctaPagarRepository.findById(id);
		if (ctaPagar.isPresent()) {
			ctaPagarRepository.deleteById(ctaPagar.get().getId());
		}
	}

	public List<CtaPagarDTO> findAll() {
		List<CtaPagarDTO> ctaPagarRetorno = new ArrayList<CtaPagarDTO>();
		List<CtaPagar> ctasPagar = ctaPagarRepository.findAll();

		for (CtaPagar ctaPagar : ctasPagar) {
			CtaPagarDTO ctaPagarDTO = new CtaPagarDTO();
			ctaPagarDTO.setCondominio(condominioService.findById(ctaPagar.getCondominio().getId()));
			ctaPagarDTO.setId(ctaPagar.getId());
			ctaPagarDTO.setData(ctaPagar.getData());
			ctaPagarDTO.setHistorico(ctaPagar.getHistorico());
			ctaPagarDTO.setDebito(ctaPagar.getDebito());
			ctaPagarDTO.setCredito(ctaPagar.getCredito());
			ctaPagarDTO.setSaldo(ctaPagar.getSaldo());
			ctaPagarDTO.setObservacao(ctaPagar.getObservacao());
			ctaPagarDTO.setDocValido(ctaPagar.getDocValido());
			ctaPagarDTO.setComprovPgto(ctaPagar.getComprovPgto());
			ctaPagarDTO.setPendente(ctaPagar.getPendente());

			ctaPagarRetorno.add(ctaPagarDTO);
		}
		return ctaPagarRetorno;

	}

	public CtaPagarDTO findById(Integer id) {
		Optional<CtaPagar> ctaPg = ctaPagarRepository.findById(id);

		if (ctaPg.isPresent()) {
			CtaPagar ctaPagar = ctaPg.get();
			CtaPagarDTO ctaPagarDTO = new CtaPagarDTO();
			ctaPagarDTO.setCondominio(condominioService.findById(ctaPagar.getCondominio().getId()));
			ctaPagarDTO.setId(ctaPagar.getId());
			ctaPagarDTO.setData(ctaPagar.getData());
			ctaPagarDTO.setHistorico(ctaPagar.getHistorico());
			ctaPagarDTO.setDebito(ctaPagar.getDebito());
			ctaPagarDTO.setCredito(ctaPagar.getCredito());
			ctaPagarDTO.setSaldo(ctaPagar.getSaldo());
			ctaPagarDTO.setObservacao(ctaPagar.getObservacao());
			ctaPagarDTO.setDocValido(ctaPagar.getDocValido());
			ctaPagarDTO.setComprovPgto(ctaPagar.getComprovPgto());
			ctaPagarDTO.setPendente(ctaPagar.getPendente());
			return ctaPagarDTO;

		}
		return null;
	}
}
