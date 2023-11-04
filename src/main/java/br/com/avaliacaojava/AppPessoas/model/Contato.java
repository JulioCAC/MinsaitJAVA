package br.com.avaliacaojava.AppPessoas.model;

import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "contato")
public class Contato {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long idContato;

	    @Column
	    private Integer tipoContato; 

	    @Column
	    private String contato;

	    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	    @JoinColumn(name = "pessoa_id")
	    private Pessoa pessoa;
	    
	

	
	public Contato() {}

	public Contato(Integer tipoContato, String contato) {
        this.tipoContato = tipoContato;
        this.contato = contato;
    }
	
	

	public Long getIdContato() {
		return idContato;
	}

	public void setIdContato(Long idContato) {
		this.idContato = idContato;
	}

	public Integer getTipoContato() {
		return tipoContato;
	}

	public void setTipoContato(Integer tipoContato) {
		this.tipoContato = tipoContato;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	

	@Override
	public int hashCode() {
		return Objects.hash(idContato);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contato other = (Contato) obj;
		return Objects.equals(idContato, other.idContato);
	}

}