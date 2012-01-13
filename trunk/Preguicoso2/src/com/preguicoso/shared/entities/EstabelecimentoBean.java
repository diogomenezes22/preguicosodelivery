package com.preguicoso.shared.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.preguicoso.shared.RegistroStatusRestaurante;

public class EstabelecimentoBean implements Serializable {

	private static final long serialVersionUID = 1921334149005836325L;

	private Long id;
	private String nome;
	private String razaoSocial;
	private String CNPJ;
	private String logoURL;
	private String categoria;
	private EnderecoBean enderecoBean;
	private Date dataRegistro;
	private Date ultimaAtualizacao;
	private UsuarioBean usuarioBean;
	private List<BairroBean> areaAtendimento;
	private boolean userIsOwner;
	private RegistroStatusRestaurante status;

	public UsuarioBean getUsuarioBean() {
		return usuarioBean;
	}

	public void setUsuarioBean(UsuarioBean usuarioBean) {
		this.usuarioBean = usuarioBean;
	}

	public RegistroStatusRestaurante getStatus() {
		return status;
	}

	public void setStatus(RegistroStatusRestaurante status) {
		this.status = status;
	}

	public EstabelecimentoBean() {
		this.nome = "";
		this.razaoSocial = "";
		this.CNPJ = "";
		this.enderecoBean = new EnderecoBean();
		this.usuarioBean = new UsuarioBean();
		this.areaAtendimento = new ArrayList<BairroBean>();
		this.userIsOwner = false;
	}

	public EstabelecimentoBean(String nome, String razaoSocial, String CNPJ) {
		this.nome = nome;
		this.razaoSocial = razaoSocial;
		this.CNPJ = CNPJ;
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

	public EnderecoBean getEnderecoBean() {
		return this.enderecoBean;
	}

	public void setEnderecoBean(EnderecoBean enderecoBean) {
		this.enderecoBean = enderecoBean;
	}

	public String getRazaoSocial() {
		return this.razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCNPJ() {
		return this.CNPJ;
	}

	public void setCNPJ(String cNPJ) {
		this.CNPJ = cNPJ;
	}

	public UsuarioBean getDono() {
		return this.usuarioBean;
	}

	public void setDono(UsuarioBean dono) {
		this.usuarioBean = dono;
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

	public List<BairroBean> getAreaAtendimento() {
		return this.areaAtendimento;
	}

	public void setAreaAtendimento(List<BairroBean> areaAtendimento) {
		this.areaAtendimento = areaAtendimento;
	}

	public boolean isUserIsOwner() {
		return this.userIsOwner;
	}

	public void setUserIsOwner(boolean userIsOwner) {
		this.userIsOwner = userIsOwner;
	}

	public void setLogoURL(String logoURL) {
		this.logoURL = logoURL;
	}

	public String getLogoURL() {
		return logoURL;
	}

	public void setCategoria(String text) {
		categoria = text;
	}

	public String getCategoria() {
		return categoria;
	}

}
