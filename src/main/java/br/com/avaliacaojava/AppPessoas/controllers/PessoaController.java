package br.com.avaliacaojava.AppPessoas.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.avaliacaojava.AppPessoas.dto.MalaDiretaDTO;
import br.com.avaliacaojava.AppPessoas.model.Pessoa;
import br.com.avaliacaojava.AppPessoas.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping("/api/pessoas") 
public class PessoaController {
	
	private PessoaService pessoaService;
	
	@Autowired
	public PessoaController(PessoaService pessoaService) {
		this.pessoaService = pessoaService;
	}
	
	@Operation(summary = "Obter todas as pessoas")
	@GetMapping
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Pessoas recuperadas com sucesso"),
	        @ApiResponse(responseCode = "404", description = "Nenhuma pessoa encontrada")
	    })
	public ResponseEntity<List<Pessoa>> getAllPessoas() {
		List<Pessoa> pessoas = pessoaService.getAll();
		if (pessoas.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(pessoas);
	}
	
	@Operation(summary = "Obter uma pessoa por ID")
	@GetMapping("/{id}")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Pessoa encontrada"),
		@ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
	})
	public ResponseEntity<Optional<Pessoa>> getPessoaById(@PathVariable Long id) {
		Optional<Pessoa> pessoa = pessoaService.getById(id);
		if (!pessoa.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(pessoa);
	}
	
	@Operation(summary = "Obter informações de mala direta para uma pessoa")
	@GetMapping("/maladireta/{id}")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Informações de mala direta obtidas"),
		@ApiResponse(responseCode = "404", description = "Pessoa não encontrada para mala direta")
	})
	public ResponseEntity<MalaDiretaDTO> obterPessoaParaMalaDireta(@PathVariable Long id) {
		Optional<Pessoa> pessoa = pessoaService.getById(id);
		if (pessoa.isPresent()) {
			MalaDiretaDTO malaDireta = MalaDiretaDTO.fromPessoa(pessoa.get());
			return ResponseEntity.ok(malaDireta);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "Criar uma nova pessoa")
	@PostMapping
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Pessoa criada com sucesso"),
		@ApiResponse(responseCode = "400", description = "Requisição inválida")
	})
	public ResponseEntity<Pessoa> save(@RequestBody Pessoa pessoa) {
		Pessoa newPessoa = pessoaService.save(pessoa);
		return ResponseEntity.ok(newPessoa);
	}
	
	@Operation(summary = "Atualizar uma pessoa por ID")
	@PutMapping("/{id}")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Pessoa atualizada com sucesso"),
		@ApiResponse(responseCode = "400", description = "Requisição inválida"),
		@ApiResponse(responseCode = "404", description = "Pessoa não encontrada para atualização")
	})
	public ResponseEntity<Pessoa> update(@PathVariable Long id, @RequestBody Pessoa pessoa) {
		Pessoa updatedPessoa = pessoaService.update(id, pessoa);
		if (updatedPessoa == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(updatedPessoa);
	}
	
	@Operation(summary = "Deletar uma pessoa por ID")
	@DeleteMapping("/{id}")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "204", description = "Pessoa excluída com sucesso"),
		@ApiResponse(responseCode = "404", description = "Pessoa não encontrada para exclusão")
	})
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Optional<Pessoa> pessoa = pessoaService.getById(id);
		if (pessoa.isPresent()) {
			pessoaService.delete(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
