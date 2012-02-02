package com.preguicoso.server.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;

import com.googlecode.objectify.annotation.Entity;
import com.preguicoso.shared.CriptoUtils;
import com.preguicoso.shared.entities.UsuarioBean;

@Entity
public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1925156038016050990L;

	@Id
	String email;
	String password;
	String logradouro;
	String numero;
	String bairro;
	String complemento;
	Long idCidade;
	String telefoneResidencial;
	String telefoneCelular;
	String nome;
	String rg;
	String cpf;
	Date dataNascimento;

	Date dataRegistro;
	Date dataUltimaAtualizacao;

	public Usuario() {

	}

	public Usuario(UsuarioBean ub) {
		this.setEmail(ub.getEmail());
		this.setPassword(ub.getPassword());
		this.setLogradouro(ub.getLogradouro());
		this.setNumero(ub.getNumero());
		this.setBairro(ub.getBairro());
		this.setComplemento(ub.getComplemento());
		this.setIdCidade(ub.getIdCidade());
		this.setTelefoneResidencial(ub.getTelefoneResidencial());
		this.setTelefoneCelular(ub.getTelefoneCelular());
		this.setNome(ub.getNome());
		this.setRg(ub.getRg());
		this.setCpf(ub.getCpf());
		this.setDataNascimento(ub.getDataNascimento());
		this.setDataRegistro(ub.getDataRegistro());
		this.setDataUltimaAtualizacao(ub.getDataUltimaAtualizacao());
	}

	public UsuarioBean toBean() {
		UsuarioBean ub = new UsuarioBean();
		ub.setEmail(email);
		ub.setPassword(password);
		ub.setLogradouro(logradouro);
		ub.setNumero(numero);
		ub.setBairro(bairro);
		ub.setComplemento(complemento);
		ub.setIdCidade(idCidade);
		ub.setTelefoneResidencial(telefoneResidencial);
		ub.setTelefoneCelular(telefoneCelular);
		ub.setNome(nome);
		ub.setRg(rg);
		ub.setCpf(cpf);
		ub.setDataNascimento(dataNascimento);
		ub.setDataRegistro(dataRegistro);
		ub.setDataUltimaAtualizacao(dataUltimaAtualizacao);
		return ub;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = CriptoUtils.parseMD5(password);
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Long getIdCidade() {
		return idCidade;
	}

	public void setIdCidade(Long idCidade) {
		this.idCidade = idCidade;
	}

	public String getTelefoneResidencial() {
		return telefoneResidencial;
	}

	public void setTelefoneResidencial(String telefoneResidencial) {
		this.telefoneResidencial = telefoneResidencial;
	}

	public String getTelefoneCelular() {
		return telefoneCelular;
	}

	public void setTelefoneCelular(String telefoneCelular) {
		this.telefoneCelular = telefoneCelular;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Date getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public Date getDataUltimaAtualizacao() {
		return dataUltimaAtualizacao;
	}

	public void setDataUltimaAtualizacao(Date dataUltimaAtualizacao) {
		this.dataUltimaAtualizacao = dataUltimaAtualizacao;
	}

}
