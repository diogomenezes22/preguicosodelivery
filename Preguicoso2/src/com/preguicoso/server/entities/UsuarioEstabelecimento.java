package com.preguicoso.server.entities;

import java.util.List;

import javax.persistence.Id;

import com.googlecode.objectify.annotation.Entity;
import com.preguicoso.shared.entities.UsuarioEstabelecimentoBean;

@Entity
public class UsuarioEstabelecimento {

	@Id
	String login;
	String password;
	String nome;
	String email;
	List<Long> idEstabelecimentoList;

	public UsuarioEstabelecimentoBean toBean() {
		UsuarioEstabelecimentoBean userBean = new UsuarioEstabelecimentoBean();
		userBean.setLogin(this.login);
		userBean.setPassword(this.password);
		userBean.setNome(this.nome);
		userBean.setEmail(this.email);
		userBean.setIdEstabelecimentoList(this.idEstabelecimentoList);
		return userBean;
	}

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
