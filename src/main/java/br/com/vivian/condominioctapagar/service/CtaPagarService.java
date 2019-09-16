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
	private CondominioRepository condominioRepository;

	@Autowired
	public CtaPagarService(CondominioRepository condominioRepository, CtaPagarRepository ctaPagarRepository) {
		this.ctaPagarRepository = ctaPagarRepository;
		this.condominioRepository = condominioRepository;
	}

	public void save(CtaPagarDTO ctaPagarDTO) {
		Optional<Condominio> condominio = condominioRepository.findById(ctaPagarDTO.getId());

		String data = ctaPagarDTO.getData();
		String historico = ctaPagarDTO.getHistorico();
		Double debito = ctaPagarDTO.getDebito();
		Double credito = ctaPagarDTO.getCredito();
		Double saldo = ctaPagarDTO.getSaldo();

		CtaPagar ctaPagar = new CtaPagar(condominio.get(), data, historico, debito, credito, saldo);
		this.ctaPagarRepository.saveAndFlush(ctaPagar);
		ctaPagarDTO.setId(ctaPagar.getId());
	}

	public void update(CtaPagarDTO ctaPagarDTO) {
		Optional<Condominio> condominio = condominioRepository.findById(ctaPagarDTO.getId());

		Integer id = ctaPagarDTO.getId();
		String data = ctaPagarDTO.getData();
		String historico = ctaPagarDTO.getHistorico();
		Double debito = ctaPagarDTO.getDebito();
		Double credito = ctaPagarDTO.getCredito();
		Double saldo = ctaPagarDTO.getSaldo();

		CtaPagar ctaPagar = new CtaPagar(condominio.get(), data, historico, debito, credito, saldo);
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
			ctaPagarDTO.setCondominio(ctaPagar.getCondominio().getId());
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
			ctaPagarDTO.setCondominio(ctaPagar.getCondominio().getId());
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
