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
    
    @Operation(summary = "Retorna os dados de um Contato por ID")
    @GetMapping("/contatos/{idContato}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Contato encontrado"),
        @ApiResponse(responseCode = "404", description = "Contato não encontrado")
    })
    public ResponseEntity<Optional<Contato>> getContatoById(@PathVariable Long idContato) {
        Optional<Contato> contato = contatoService.getById(idContato);
        if (contato == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(contato);
    }

    @Operation(summary = "Lista todos os Contatos de uma Pessoa")
    @GetMapping("{idPessoa}/listacontatos")
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
    
    @Operation(summary = "Adiciona um novo Contato a uma Pessoa")
    @PostMapping("/{idPessoa}/contatos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Contato criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida"),
        @ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
    })
    public ResponseEntity<Contato> save(@RequestBody Contato contato) {
		Contato newContato = contatoService.save(contato);
		return ResponseEntity.ok(newContato);
	}

    @Operation(summary = "Atualiza um Contato existente")
    @PutMapping("/contatos/{idContato}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Contato atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Contato não encontrado")
    })
    public ResponseEntity<Contato> update(@PathVariable Long idContato, @RequestBody Contato contato) {
		Contato updatedContato = contatoService.update(idContato, contato);
		if (updatedContato == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(updatedContato);
	}

    @Operation(summary = "Remove um Contato por ID")
    @DeleteMapping("/contatos/{idContato}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Contato removido com sucesso"),
        @ApiResponse(responseCode = "404", description = "Contato não encontrado")
    })
    public ResponseEntity<?> delete(@PathVariable Long idContato) {
        Optional<Contato> contato = contatoService.getById(idContato);
        if (contato.isPresent()) {
            contatoService.delete(idContato);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
