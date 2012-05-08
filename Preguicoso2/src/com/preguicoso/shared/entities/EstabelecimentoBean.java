package com.preguicoso.shared.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.preguicoso.shared.RegistroCategoriaEstabelecimento;
import com.preguicoso.shared.RegistroFormaPagamento;
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
	private List<Long> listaIdBairrosAtendidos;
	private List<Long> listaFretes;
	private Long idCidade;
	private String telefone;
	private String endereco;
	String descricao;
	private RegistroFormaPagamento[] formasPagamento;
	private String[] horariosFuncionamento;

	public Long getFreteByIdBairro(int idBairro) {
		return listaFretes.get(listaIdBairrosAtendidos.indexOf(idBairro));
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

	public String[] getHorariosFuncionamento() {
		return horariosFuncionamento;
	}

	public void setHorariosFuncionamento(String[] horariosFuncionamento) {
		this.horariosFuncionamento = horariosFuncionamento;
	}

	public Long getIdCidade() {
		return idCidade;
	}

	public void setIdCidade(Long idCidade) {
		this.idCidade = idCidade;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public List<Long> getListaIdBairrosAtendidos() {
		return listaIdBairrosAtendidos;
	}

	public void setListaIdBairrosAtendidos(List<Long> listaIdBairrosAtendidos) {
		this.listaIdBairrosAtendidos = listaIdBairrosAtendidos;
	}

	public List<Long> getListaFretes() {
		return listaFretes;
	}

	public void setListaFretes(List<Long> listaFretes) {
		this.listaFretes = listaFretes;
	}

	public RegistroFormaPagamento[] getFormasPagamento() {
		return formasPagamento;
	}

	public void setFormasPagamento(RegistroFormaPagamento[] formasPagamento) {
		this.formasPagamento = formasPagamento;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getDescricao() {
		// TODO Auto-generated method stub
		return descricao;
	}
	public void setDescricao(String descricao){
		this.descricao = descricao;
	}

}
