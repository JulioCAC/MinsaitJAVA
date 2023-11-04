package br.com.avaliacaojava.AppPessoas.service.interfaces;

import java.util.List;
import java.util.Optional;

import br.com.avaliacaojava.AppPessoas.model.Contato;


public interface ContatoServiceInterface {
	Contato save(Contato contato);
	Optional<Contato> getById(Long idContato);
	List<Contato> getAll();
	Contato update(Long id, Contato contato);
	void delete(Long idContato);
}
