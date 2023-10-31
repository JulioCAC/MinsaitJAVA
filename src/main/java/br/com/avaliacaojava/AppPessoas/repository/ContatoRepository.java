package br.com.avaliacaojava.AppPessoas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.avaliacaojava.AppPessoas.model.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {

}