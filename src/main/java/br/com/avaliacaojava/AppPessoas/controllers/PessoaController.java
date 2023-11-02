package br.com.avaliacaojava.AppPessoas.controllers;

import java.util.List;
import java.util.Optional;

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

import br.com.avaliacaojava.AppPessoas.model.Pessoa;
import br.com.avaliacaojava.AppPessoas.service.PessoaService;

@RestController
@RequestMapping("/api/pessoas") //http://localhost:8080/api/produtos
public class PessoaController {
	
	private PessoaService pessoaService;
	
	@Autowired
	public PessoaController(PessoaService pessoaService) {
		this.pessoaService = pessoaService;
	}
	
	@GetMapping
	public ResponseEntity<List<Pessoa>> getAllProdutos(){
		List<Pessoa> produtos = pessoaService.getAll();
		if(produtos == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(produtos);
	}
	
	@GetMapping("/{id}") //http://localhost:8080/api/produtos/12
	public ResponseEntity<Optional<Pessoa>> getById(@PathVariable Long id){
		Optional<Pessoa> produto = pessoaService.getById(id);
		if(produto == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(produto);
	}
	
	@PostMapping
	public ResponseEntity<Pessoa> save(@RequestBody Pessoa produto){
		Pessoa newProduto = pessoaService.save(produto);
		if(newProduto == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(newProduto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Pessoa> update(@PathVariable Long id, @RequestBody Pessoa produto){
		Pessoa updateProduto = pessoaService.update(id, produto);
		if(updateProduto == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(updateProduto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		pessoaService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}