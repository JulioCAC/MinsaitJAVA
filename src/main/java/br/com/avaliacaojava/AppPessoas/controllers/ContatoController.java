package br.com.avaliacaojava.AppPessoas.controllers;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.avaliacaojava.AppPessoas.model.Contato;
import br.com.avaliacaojava.AppPessoas.service.ContatoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping("/api/pessoas")
public class ContatoController {

    private ContatoService contatoService;

    @Autowired
    public ContatoController(ContatoService contatoService) {
        this.contatoService = contatoService;
    }

    @PostMapping("/{id}/contatos")
    @Operation(summary = "Adiciona um novo Contato a uma Pessoa")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Contato criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida"),
        @ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
    })
    public ResponseEntity<Contato> save(@RequestBody Contato contato) {
		Contato newContato = contatoService.save(contato);
		return ResponseEntity.ok(newContato);
	}

    @GetMapping("/{id}/contatos")
    @Operation(summary = "Retorna os dados de um Contato por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Contato encontrado"),
        @ApiResponse(responseCode = "404", description = "Contato não encontrado")
    })
    public ResponseEntity<List<Contato>> getAllContato() {
        List<Contato> contatos = contatoService.getAll();
        if (contatos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(contatos);
    }

    @GetMapping("{idPessoa}/listacontatos")
    @Operation(summary = "Lista todos os Contatos de uma Pessoa")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Contatos da Pessoa encontrados"),
        @ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
    })
    public ResponseEntity<List<Contato>> getAllPessoas() {
		List<Contato> contatos = contatoService.getAll();
		if (contatos.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(contatos);
	}

    @PutMapping("/{id}/contatos")
    @Operation(summary = "Atualiza um Contato existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Contato atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Contato não encontrado")
    })
    public ResponseEntity<Contato> update(@PathVariable Long id, @RequestBody Contato contato) {
		Contato updatedContato = contatoService.update(id, contato);
		if (updatedContato == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(updatedContato);
	}

    @DeleteMapping("/{id}/contatos")
    @Operation(summary = "Remove um Contato por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Contato removido com sucesso"),
        @ApiResponse(responseCode = "404", description = "Contato não encontrado")
    })
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Contato> contato = contatoService.getById(id);
        if (contato.isPresent()) {
            contatoService.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
