package com.preguicoso.server.dbgenerator;

public class Bairropg {
	private Long cd_bairro;
	private Long cd_cidade;
	private String ds_bairro_nome;

	public Bairropg(Long cd_bairro, Long cd_cidade, String ds_bairro_nome) {
		this.cd_bairro = cd_bairro;
		this.cd_cidade = cd_cidade;
		this.ds_bairro_nome = ds_bairro_nome;
	}

	public String getDs_bairro_nome() {
		return ds_bairro_nome;
	}

	public void setDs_bairro_nome(String ds_bairro_nome) {
		this.ds_bairro_nome = ds_bairro_nome;
	}

	public Long getCd_bairro() {
		return cd_bairro;
	}

	public void setCd_bairro(Long cd_bairro) {
		this.cd_bairro = cd_bairro;
	}

	public Long getCd_cidade() {
		return cd_cidade;
	}

	public void setCd_cidade(Long cd_cidade) {
		this.cd_cidade = cd_cidade;
	}

}
