package com.preguicoso.shared.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.preguicoso.shared.RegistroCategoriaEstabelecimento;
import com.preguicoso.shared.RegistroStatusRestaurante;

public class EstabelecimentoBean implements Serializable {

	private static final long serialVersionUID = 1921334149005836325L;

	private Long id;
	private String nome;
	private String razaoSocial;
	private String cnpj;
	private String logoURL;
	private RegistroCategoriaEstabelecimento categoria;
	private Date dataRegistro;
	private Date ultimaAtualizacao;
	private RegistroStatusRestaurante status;
	private List<Long> idBairroAtendimentoList;
	private Long idCidade;

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
