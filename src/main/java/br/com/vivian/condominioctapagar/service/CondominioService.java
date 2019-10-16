package br.com.vivian.condominioctapagar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vivian.condominioctapagar.domain.Condominio;
import br.com.vivian.condominioctapagar.dto.CondominioDTO;
import br.com.vivian.condominioctapagar.repository.CondominioRepository;

@Service
public class CondominioService {

	private CondominioRepository condominioRepository;

	@Autowired
	public CondominioService(CondominioRepository condominioRepository) {
		this.condominioRepository = condominioRepository;
	}
	public void save(CondominioDTO condominioDTO) {
		String nome = condominioDTO.getNome();
		String cnpj = condominioDTO.getCnpj();
		String contato = condominioDTO.getContato();
		
		Condominio condominio = new Condominio(nome,cnpj,contato);
		validarInsertCondominio(condominio);
		this.condominioRepository.save(condominio);
	}
	
	private void validarInsertCondominio(Condominio condominio) {
		Long numberOfCondominioWithCNPJ = condominioRepository.validateExistClientByCnpj(condominio.getCnpj());
		if (numberOfCondominioWithCNPJ > 0) {
			throw new ServiceException("Condominio já cadastrado");
		}
		
	}
	public Condominio findByCnpj(String cnpj) {
		Optional<Condominio> condominioEncontrado = condominioRepository.findByCnpj(cnpj);
		if(condominioEncontrado.isPresent()) {
			return condominioEncontrado.get();
		}
		throw new ServiceException("Condominio não encontrado");
	}
	public Condominio findById(Integer id) {
		Optional<Condominio>condominioIdEnc = condominioRepository.findById(id);
		if (condominioIdEnc.isPresent()) {
			return condominioIdEnc.get();
		}
		throw new ServiceException("Id condominio não encontrado");
	}
	public void deleteAll() {
		this.condominioRepository.deleteAll();
	}
	public void deleteBycnpj(String cnpj) {
		Optional<Condominio> condominio = condominioRepository.findByCnpj(cnpj);
		if(condominio.isPresent()) {
			condominioRepository.deleteById(condominio.get().getId());
		}
	}
	public List<CondominioDTO> findAll(){
		List<CondominioDTO> condominioRetorno = new ArrayList<CondominioDTO>();
		List<Condominio> condominios = condominioRepository.findAll();
		
		for(Condominio condominio : condominios) {
			CondominioDTO condominioDTO = new CondominioDTO();
			condominioDTO.setNome(condominio.getNome());
			condominioDTO.setCnpj(condominio.getCnpj());
			condominioDTO.setContato(condominio.getContato());
			
			condominioRetorno.add(condominioDTO);
			
		}
		return condominioRetorno;
	}
	

}
