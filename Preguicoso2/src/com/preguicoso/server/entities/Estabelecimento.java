package com.preguicoso.server.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;

import com.googlecode.objectify.annotation.Cached;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Indexed;
import com.preguicoso.server.dao.BairroDAO;
import com.preguicoso.server.dao.EnderecoDAO;
import com.preguicoso.server.dao.UsuarioDAO;
import com.preguicoso.shared.RegistroStatusRestaurante;
import com.preguicoso.shared.entities.BairroBean;
import com.preguicoso.shared.entities.EstabelecimentoBean;

@Entity
@Cached
public class Estabelecimento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	Long id;
	@Column
	String nome;
	@Indexed
	Long enderecoId;
	@Column
	String razaoSocial;
	@Indexed
	String cnpj;
	@Column
	String logoURL = "";
	@Column
	String categoria = "";
	RegistroStatusRestaurante status;

	public Long getEnderecoId() {
		return enderecoId;
	}

	public void setEnderecoId(Long enderecoId) {
		this.enderecoId = enderecoId;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public RegistroStatusRestaurante getStatus() {
		return status;
	}

	public void setStatus(RegistroStatusRestaurante status) {
		this.status = status;
	}

	public List<String> getListaCep() {
		return listaCep;
	}

	public void setListaCep(List<String> listaCep) {
		this.listaCep = listaCep;
	}

	public String getEmailDono() {
		return emailDono;
	}

	public void setEmailDono(String emailDono) {
		this.emailDono = emailDono;
	}

	public String getCategoria() {
		return this.categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getLogoURL() {
		return this.logoURL;
	}

	public void setLogoURL(String logoURL) {
		this.logoURL = logoURL;
	}

	Date dataRegistro;
	Date ultimaAtualizacao;
	List<String> listaCep;
	String emailDono;

	public Estabelecimento() {
		this.nome = "";
		this.razaoSocial = "";
		this.cnpj = "";
		this.listaCep = new ArrayList<String>();
	}

	public Estabelecimento(String nome, String razaoSocial, String CNPJ) {
		this();
		this.nome = nome;
		this.razaoSocial = razaoSocial;
		this.cnpj = CNPJ;
	}

	public Estabelecimento(EstabelecimentoBean a) {
		this();
		this.nome = a.getNome();
		this.razaoSocial = a.getRazaoSocial();
		this.cnpj = a.getCNPJ();
		this.id = a.getId();
		this.dataRegistro = a.getDataRegistro();
		this.ultimaAtualizacao = a.getUltimaAtualizacao();
		this.logoURL = a.getLogoURL();

		try {
			for (BairroBean b : a.getAreaAtendimento()) {
				this.listaCep.add(b.getCep());
				this.listaCep = new ArrayList<String>();
				this.enderecoId = a.getEnderecoBean().getId();
				this.emailDono = a.getDono().getEmail();
			}
		} catch (Exception e) {

		}
		this.categoria = a.getCategoria();

	}

	public EstabelecimentoBean toBean() {
		EstabelecimentoBean bean = new EstabelecimentoBean();
		bean.setCNPJ(this.cnpj);
		bean.setId(this.id);
		bean.setNome(this.nome);
		// bean.setEnderecoBean(this.getEndereco().toBean());
		bean.setRazaoSocial(this.razaoSocial);
		bean.setDataRegistro(this.dataRegistro);
		bean.setUltimaAtualizacao(this.ultimaAtualizacao);
		bean.setLogoURL(this.logoURL);
		bean.setAreaAtendimento(this.getAreaAtendimentoBean());
		bean.setStatus(this.status);

		// bean.setDono(this.getDono().toBean());
		// bean.setAreaAtendimento(this.getAreaAtendimentoBean());

		return bean;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Endereco getEndereco() {
		Endereco endereco = (new EnderecoDAO()).retrieve(this.enderecoId);
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.enderecoId = endereco.getId();
	}

	public String getRazaoSocial() {
		return this.razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public Date getDataRegistro() {
		return this.dataRegistro;
	}

	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public Date getUltimaAtualizacao() {
		return this.ultimaAtualizacao;
	}

	public void setUltimaAtualizacao(Date ultimaAtualizacao) {
		this.ultimaAtualizacao = ultimaAtualizacao;
	}

	public List<Bairro> getAreaAtendimento() {
		List<Bairro> lista = new ArrayList<Bairro>();
		BairroDAO bairroDAO = new BairroDAO();
		Bairro bairro;
		for (String cep : this.listaCep) {
			bairro = bairroDAO.retrieveByCep(cep);
			lista.add(bairro);
		}
		return lista;
	}

	public List<BairroBean> getAreaAtendimentoBean() {
		List<BairroBean> lista = new ArrayList<BairroBean>();
		BairroDAO bairroDAO = new BairroDAO();
		Bairro bairro;
		for (String cep : this.listaCep) {
			bairro = bairroDAO.retrieveByCep(cep);
			lista.add(bairro.toBean());
		}
		return lista;
	}

	public void setAreaAtendimento(List<Bairro> lista) {
		List<String> listaCep = new ArrayList<String>();
		for (Bairro b : lista) {
			listaCep.add(b.getCep());
		}
		this.listaCep = listaCep;
	}

	public Usuario getDono() {
		Usuario dono = (new UsuarioDAO()).retrieve(this.emailDono);
		return dono;
	}

	public void setDono(Usuario dono) {
		this.emailDono = dono.getEmail();
	}
}