package br.com.avaliacaojava.AppPessoas.dto;

import br.com.avaliacaojava.AppPessoas.model.Pessoa;

public record MalaDiretaDTO(long Id, String Nome, String MalaDireta) {
    

    public static MalaDiretaDTO fromPessoa(Pessoa pessoa) {
    
        String malaDireta = pessoa.getEndereco() + " - CEP: " + pessoa.getCEP() + " - " +
                            pessoa.getCidade() + "/" + pessoa.getUF();

        return new MalaDiretaDTO(pessoa.getId(), pessoa.getNome(), malaDireta);
    }
}