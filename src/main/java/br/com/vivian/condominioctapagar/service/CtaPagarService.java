package br.com.vivian.condominioctapagar.service;

import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;

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

		this.ctaPagarRepository.deleteById(id);
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

	public void importacao(String body) {

		CSVReader reader = new CSVReader(new StringReader(body), ';');
		// CSVReader arqCsv = new CSVReader(new FileReader("ExtratoCC.csv"), ';');

		List<CtaPagar> ctaPagar1 = new ArrayList<CtaPagar>();

		// read line by line
		String[] record = null;
		

		try {
			while ((record = reader.readNext()) != null) {
				CtaPagar ctaPagar = new CtaPagar();
				// ctaPagar.setId(Integer.parseInt(record[0]));
				
				String debito = record[2];
				String credito = record[3];
				String saldo = record[4];
				
				
								
				debito = debito.replaceAll(".", "");// Remove ponto
				debito = debito.replaceAll(",", ".");// troca vírgula pelo ponto formato americano
				
				
				credito = credito.replaceAll(".", "");// Remove ponto
				credito = credito.replaceAll(",", ".");// troca vírgula pelo ponto formato americano
				
				
				saldo = saldo.replaceAll(".", "");// Remove ponto
				saldo = saldo.replaceAll(",", ".");// troca vírgula pelo ponto formato americano
				
				if( debito.isEmpty()){
					debito = "0";
					}
				if(credito.isEmpty()) {
					credito = "0";
				}
				if(saldo.isEmpty()) {
					saldo = "0";
				}
				
				
				ctaPagar.setData(record[0]);
				ctaPagar.setHistorico(record[1]);
				ctaPagar.setDebito(Double.parseDouble(debito));
				ctaPagar.setCredito(Double.parseDouble(credito));
				ctaPagar.setSaldo(Double.parseDouble(saldo));
				ctaPagar.setObservacao(record[5]);
				ctaPagar.setDocValido(record[6]);
				ctaPagar.setComprovPgto(record[7]);
				ctaPagar.setPendente(record[8]);

				// adicionar salvar banco

				this.ctaPagarRepository.saveAndFlush(ctaPagar);
				ctaPagar1.add(ctaPagar);
			}
		} catch (NumberFormatException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		System.out.println(ctaPagar1);

	}

}
