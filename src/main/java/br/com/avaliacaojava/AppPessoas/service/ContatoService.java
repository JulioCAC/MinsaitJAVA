package br.com.avaliacaojava.AppPessoas.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.avaliacaojava.AppPessoas.model.Contato;
import br.com.avaliacaojava.AppPessoas.repository.ContatoRepository;
import br.com.avaliacaojava.AppPessoas.service.interfaces.ContatoServiceInterface;

@Service
public class ContatoService implements ContatoServiceInterface {

	private ContatoRepository contatoRepository;
	
	@Autowired
	public ContatoService(ContatoRepository contatoRepository) {
		this.contatoRepository = contatoRepository;
	}
	
	@Override
	public Contato save(Contato contato) {
		return contatoRepository.save(contato);
	}

	@Override
	public Optional<Contato> getById(Long idContato) {
		return contatoRepository.findById(idContato);
	}

	@Override
	public List<Contato> getAll() {
		return contatoRepository.findAll();
	}

	@Override
	public Contato update(Long idContato, Contato contato) {
		
		Optional<Contato> upContato = contatoRepository.findById(contato.getIdContato());
		
		
		if(upContato.isPresent()) {
			Contato newContato = upContato.get();
			newContato.setTipoContato(contato.getTipoContato());
			newContato.setContato(contato.getContato());
			return contatoRepository.save(newContato);
		}
		return contato;
	}

	@Override
	public void delete(Long idContato) {
		contatoRepository.deleteById(idContato);
	}
	
}
	