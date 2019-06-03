package br.com.blz.request;

import java.io.Serializable;

/**
 * Classe que representa a saída padrão para todos os serviços REST do sistema
 * 
 * Esta classe deve ser colocada em um jar separado o jar ser uma dependencia desse projeto
 * 
 * @author tiago
 */
public class SaidaPadrao implements Serializable {

	/**
	 * Serial Id.
	 */
	private static final long serialVersionUID = -224841311130028923L;
	
	/**
	 * Objeto de Retorno, pode armazenar qualquer tipo de objeto conforme o
	 * retorno específico do serviço
	 */
	private Object objeto;

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

	public static SaidaPadrao montaSaidaComSucesso() {
		final SaidaPadrao retornoSucesso = new SaidaPadrao();
		return retornoSucesso;
	}
}
