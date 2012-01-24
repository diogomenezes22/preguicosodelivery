package com.preguicoso.server.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Id;

import com.googlecode.objectify.annotation.Cached;
import com.googlecode.objectify.annotation.Entity;
import com.preguicoso.shared.RegistroCategoriaEstabelecimento;
import com.preguicoso.shared.RegistroStatusRestaurante;
import com.preguicoso.shared.entities.EstabelecimentoBean;

@Entity
@Cached
public class Estabelecimento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3450285798362389924L;
	@Id
	Long id;
	String nome;
	String razaoSocial;
	String cnpj;
	String logoURL;
	RegistroCategoriaEstabelecimento categoria;
	Date dataRegistro;
	Date ultimaAtualizacao;
	RegistroStatusRestaurante status;
	List<Long> idBairroAtendimentoList;
	Long idCidade;

	public Estabelecimento() {
	};

	public Estabelecimento(EstabelecimentoBean eb) {
		this.setId(eb.getId());
		this.setNome(eb.getNome());
		this.setRazaoSocial(eb.getRazaoSocial());
		this.setCnpj(eb.getCnpj());
		this.setLogoURL(eb.getLogoURL());
		this.setCategoria(eb.getCategoria());
		this.setDataRegistro(eb.getDataRegistro());
		this.setUltimaAtualizacao(eb.getUltimaAtualizacao());
		this.setStatus(eb.getStatus());
		this.setIdBairroAtendimentoList(eb.getIdBairroAtendimentoList());
		this.setIdCidade(eb.getIdCidade());
	}

	public EstabelecimentoBean toBean() {
		EstabelecimentoBean eb = new EstabelecimentoBean();
		eb.setId(this.id);
		eb.setNome(this.nome);
		eb.setRazaoSocial(this.razaoSocial);
		eb.setCnpj(this.cnpj);
		eb.setLogoURL(this.logoURL);
		eb.setCategoria(this.categoria);
		eb.setDataRegistro(this.dataRegistro);
		eb.setUltimaAtualizacao(this.ultimaAtualizacao);
		eb.setStatus(this.status);
		eb.setIdBairroAtendimentoList(this.idBairroAtendimentoList);
		eb.setIdCidade(idCidade);
		return eb;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getLogoURL() {
		return logoURL;
	}

	public void setLogoURL(String logoURL) {
		this.logoURL = logoURL;
	}

	public RegistroCategoriaEstabelecimento getCategoria() {
		return categoria;
	}

	public void setCategoria(RegistroCategoriaEstabelecimento categoria) {
		this.categoria = categoria;
	}

	public Date getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public Date getUltimaAtualizacao() {
		return ultimaAtualizacao;
	}

	public void setUltimaAtualizacao(Date ultimaAtualizacao) {
		this.ultimaAtualizacao = ultimaAtualizacao;
	}

	public RegistroStatusRestaurante getStatus() {
		return status;
	}

	public void setStatus(RegistroStatusRestaurante status) {
		this.status = status;
	}

	public List<Long> getIdBairroAtendimentoList() {
		return idBairroAtendimentoList;
	}

	public void setIdBairroAtendimentoList(List<Long> idBairroAtendimentoList) {
		this.idBairroAtendimentoList = idBairroAtendimentoList;
	}

	public Long getIdCidade() {
		return idCidade;
	}

	public void setIdCidade(Long idCidade) {
		this.idCidade = idCidade;
	}

}