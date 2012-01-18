package com.preguicoso.shared.entities;

import java.util.List;

public class UsuarioEstabelecimentoBean {

	String login;
	String password;
	String nome;
	String email;
	List<Long> idEstabelecimentoList;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Long> getIdEstabelecimentoList() {
		return idEstabelecimentoList;
	}

	public void setIdEstabelecimentoList(List<Long> idEstabelecimentoList) {
		this.idEstabelecimentoList = idEstabelecimentoList;
	}

}
