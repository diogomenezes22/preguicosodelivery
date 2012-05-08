package com.preguicoso.server.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Id;

import com.googlecode.objectify.annotation.Entity;
import com.preguicoso.shared.RegistroCategoriaEstabelecimento;
import com.preguicoso.shared.RegistroFormaPagamento;
import com.preguicoso.shared.RegistroStatusRestaurante;
import com.preguicoso.shared.entities.EstabelecimentoBean;

@Entity
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
	String descricao;
	

	RegistroCategoriaEstabelecimento categoria;
	Date dataRegistro;
	Date ultimaAtualizacao;
	RegistroStatusRestaurante status;
	List<Long> listaIdBairrosAtendidos;
	List<Long> listaFretes;
	Long idCidade;
	String telefone;
	String endereco;
	RegistroFormaPagamento[] formasPagamento;
	String[] horariosFuncionamento;

	public Long getFreteByIdBairro(int idBairro) {
		return listaFretes.get(listaIdBairrosAtendidos.indexOf(idBairro));
	}

	public void putBairroFrete(Long idBairro, Long frete) {
		listaIdBairrosAtendidos.add(idBairro);
		listaFretes.add(frete);
	}

	public Estabelecimento() {
		listaIdBairrosAtendidos = new ArrayList<Long>();
		listaFretes = new ArrayList<Long>();
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

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
		this.setListaIdBairrosAtendidos(eb.getListaIdBairrosAtendidos());
		this.setListaFretes(eb.getListaFretes());
		this.setIdCidade(eb.getIdCidade());
		this.setTelefone(eb.getTelefone());
		this.setEndereco(eb.getEndereco());
		this.setDescricao(eb.getDescricao());
		this.setFormasPagamento(eb.getFormasPagamento());
		this.setHorariosFuncionamento(eb.getHorariosFuncionamento());
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
		eb.setListaIdBairrosAtendidos(this.listaIdBairrosAtendidos);
		eb.setListaFretes(this.listaFretes);
		eb.setIdCidade(this.idCidade);
		eb.setTelefone(this.telefone);
		eb.setEndereco(this.endereco);
		eb.setDescricao(this.descricao);
		eb.setFormasPagamento(this.formasPagamento);
		eb.setHorariosFuncionamento(this.horariosFuncionamento);
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

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public RegistroFormaPagamento[] getFormasPagamento() {
		return formasPagamento;
	}

	public void setFormasPagamento(RegistroFormaPagamento[] formasPagamento) {
		this.formasPagamento = formasPagamento;
	}

}