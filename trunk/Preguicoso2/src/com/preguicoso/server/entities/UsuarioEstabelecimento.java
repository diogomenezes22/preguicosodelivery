package com.preguicoso.server.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Id;

import com.googlecode.objectify.annotation.Entity;
import com.preguicoso.shared.entities.UsuarioEstabelecimentoBean;
import com.preguicoso.shared.utils.CriptoUtils;

@Entity
public class UsuarioEstabelecimento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5206607061710747182L;
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

	/**
	 * <p>
	 * A senha será convertida para o modo MD5 e depois armazenada
	 * </p>
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = CriptoUtils.parseMD5(password);
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
