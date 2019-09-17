package br.com.vivian.condominioctapagar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vivian.condominioctapagar.domain.Condominio;
import br.com.vivian.condominioctapagar.domain.CtaPagar;
import br.com.vivian.condominioctapagar.dto.CtaPagarDTO;
import br.com.vivian.condominioctapagar.repository.CondominioRepository;
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
	Condominio condominio = condominioService.findById(ctaPagarDTO.getId());

		String data = ctaPagarDTO.getData();
		String historico = ctaPagarDTO.getHistorico();
		Double debito = ctaPagarDTO.getDebito();
		Double credito = ctaPagarDTO.getCredito();
		Double saldo = ctaPagarDTO.getSaldo();

		CtaPagar ctaPagar = new CtaPagar(condominio, data, historico, debito, credito, saldo);
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

		CtaPagar ctaPagar = new CtaPagar(condominio, data, historico, debito, credito, saldo);
		this.ctaPagarRepository.saveAndFlush(ctaPagar);

	}

	public void deletAll() {
		this.ctaPagarRepository.deleteAll();

	}

	public List<CtaPagarDTO> findAll() {
		List<CtaPagarDTO> ctaPagarRetorno = new ArrayList<CtaPagarDTO>();
		List<CtaPagar> ctasPagar = ctaPagarRepository.findAll();

		for (CtaPagar ctaPagar : ctasPagar) {
			CtaPagarDTO ctaPagarDTO = new CtaPagarDTO();
			ctaPagarDTO.setCondominio(condominioService.findById(ctaPagar.getCondominio().getId()));
			ctaPagarDTO.setData(ctaPagar.getData());
			ctaPagarDTO.setHistorico(ctaPagar.getHistorico());
			ctaPagarDTO.setDebito(ctaPagar.getDebito());
			ctaPagarDTO.setCredito(ctaPagar.getCredito());
			ctaPagarDTO.setSaldo(ctaPagar.getSaldo());

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
			ctaPagarDTO.setData(ctaPagar.getData());
			ctaPagarDTO.setHistorico(ctaPagar.getHistorico());
			ctaPagarDTO.setDebito(ctaPagar.getDebito());
			ctaPagarDTO.setCredito(ctaPagar.getCredito());
			ctaPagarDTO.setSaldo(ctaPagar.getSaldo());
			return ctaPagarDTO;

		}
		return null;
	}
}
