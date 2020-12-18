package br.com.blz.testjava.model.enums;

import lombok.Getter;

@Getter
public enum ProblemTypeEnum {
	CADASTRO_INATIVO("/cadastro-inativo", "Cadastro inativo no sistema."),
	ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não encontrada."),
    ENTIDADE_DUPLICADA("/entidade-duplicada", "Entidade já salva no banco de dados e não pode ser duplicada."),
	ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso."),
	DADOS_INVALIDOS("/dados-invalidos","Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.");

	private String title;
	private String uri;

	ProblemTypeEnum(String path, String title) {
		this.uri = "https://localhost.com.br" + path;
		this.title = title;
	}
}
