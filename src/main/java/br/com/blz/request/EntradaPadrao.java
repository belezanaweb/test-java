package br.com.blz.request;

import java.io.Serializable;

/**
 * Classe que representa a entrada padrão para todos os serviços REST do sistema
 * 
 * Esta classe deve ser colocada em um jar separado o jar ser uma dependencia desse projeto
 * 
 * @author tiago
 */
public class EntradaPadrao implements Serializable {

	/**
	 * Serial Id
	 */
	private static final long serialVersionUID = 4721758979522670041L;

	/**
	 * Token de controle de login
	 */
	private String token;

	/**
	 * Objeto de Entrada, pode armazenar qualquer tipo de objeto conforme a
	 * necessidade específica do serviço
	 */
	private Object objeto;

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token
	 *            the token to set
	 */
	public void setToken(final String token) {
		this.token = token;
	}

	/**
	 * @return the objeto
	 */
	public Object getObjeto() {
		return objeto;
	}

	/**
	 * @param objeto
	 *            the objeto to set
	 */
	public void setObjeto(final Object objeto) {
		this.objeto = objeto;
	}

}
