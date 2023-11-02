package br.com.avaliacaojava.AppPessoas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.avaliacaojava.AppPessoas.model.Pessoa;
import br.com.avaliacaojava.AppPessoas.repository.PessoaRepository;
import br.com.avaliacaojava.AppPessoas.service.interfaces.PessoaServiceInterface;

@Service
public class PessoaService implements PessoaServiceInterface {

	private PessoaRepository pessoaRepository;
	
	@Autowired
	public PessoaService(PessoaRepository pessoaRepository) {
		this.pessoaRepository = pessoaRepository;
	}
	
	@Override
	public Pessoa save(Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}

	@Override
	public Optional<Pessoa> getById(Long id) {
		return pessoaRepository.findById(id);
	}

	@Override
	public List<Pessoa> getAll() {
		return pessoaRepository.findAll();
	}

	@Override
	public Pessoa update(Long id, Pessoa pessoa) {
		
		Optional<Pessoa> upPessoa = pessoaRepository.findById(pessoa.getId());
		
		
		if(upPessoa.isPresent()) {
			Pessoa newPessoa = upPessoa.get();
			newPessoa.setNome(pessoa.getNome());
			newPessoa.setEndereco(pessoa.getEndereco());
			newPessoa.setCEP(pessoa.getCEP());
			newPessoa.setCidade(pessoa.getCidade());
			newPessoa.setUF(pessoa.getUF());
			return pessoaRepository.save(newPessoa);
		}
		return pessoa;
	}

	@Override
	public void delete(Long id) {
		pessoaRepository.deleteById(id);
	}

}